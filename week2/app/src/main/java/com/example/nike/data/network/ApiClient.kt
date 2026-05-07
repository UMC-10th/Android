package com.example.nike.data.network

import android.util.Log
import com.example.nike.data.service.ProfileService
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    private const val BASE_URL = "https://reqres.in/"

    private val loggingInterceptor = HttpLoggingInterceptor { message ->
        when {
            !message.isJsonObject() && !message.isJsonArray() ->
                Log.d("RETROFIT", "CONNECTION INFO -> $message")

            else ->
                try {
                    Log.d(
                        "RETROFIT",
                        GsonBuilder().setPrettyPrinting().create().toJson(
                            JsonParser.parseString(message)
                        )
                    )
                } catch (m: JsonSyntaxException) {
                    Log.d("RETROFIT", message)
                }
        }
    }.apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val profileService: ProfileService = retrofit.create(ProfileService::class.java)
}

fun String.isJsonObject(): Boolean = startsWith("{") && endsWith("}")
fun String.isJsonArray(): Boolean = startsWith("[") && endsWith("]")