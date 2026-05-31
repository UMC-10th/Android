package com.example.nike.data.remote

import com.example.nike.BuildConfig
import com.example.nike.data.remote.api.ProfileApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object RetrofitClient {
    private const val BASE_URL = BuildConfig.BASE_URL
    private val json = Json { ignoreUnknownKeys = true }

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("x-api-key", BuildConfig.API_KEY)
                .build()
            chain.proceed(request)
        }
        .build()

    private val instance: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory("application/json; charset=UTF-8".toMediaType()))
        .build()

    fun <T> create(service: Class<T>): T = instance.create(service)

    val profileApi: ProfileApi by lazy {
        create(ProfileApi::class.java)
    }
}
