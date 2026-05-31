package com.example.NikeApp.data.remote

import com.example.NikeApp.BuildConfig
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

/**
 * Retrofit + OkHttp 설정을 한 곳에서 관리하는 싱글톤.
 *  - 모든 요청에 ReqRes 가 요구하는 `x-api-key` 헤더를 자동으로 붙입니다.
 *  - API 키는 .env -> BuildConfig.REQRES_API_KEY 를 통해 주입됩니다.
 */
object ReqResClient {

    private const val BASE_URL = "https://reqres.in/"

    private val json = Json {
        ignoreUnknownKeys = true // 응답에 모르는 필드가 있어도 무시
    }

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        // 1) API 키 헤더 자동 추가
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("x-api-key", BuildConfig.REQRES_API_KEY)
                .build()
            chain.proceed(request)
        }
        // 2) 요청/응답 로깅 (디버깅용)
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
        .build()

    val api: ReqResApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()
        .create(ReqResApi::class.java)
}
