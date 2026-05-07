package com.example.nike

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

import com.example.nike.BuildConfig // 본인 프로젝트 패키지명에 맞게 import 확인

object ApiClient {
    private const val BASE_URL = "https://reqres.in/"

    // 하드코딩 삭제! BuildConfig에서 가져옵니다.
    private val API_KEY = BuildConfig.REQRES_API_KEY

    private val headerInterceptor = Interceptor { chain ->
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .addHeader("x-api-key", API_KEY) // 이제 안전하게 들어갑니다.
            .build()
        chain.proceed(newRequest)
    }

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(headerInterceptor) // 통신할 때마다 헤더를 가로채서 API 키를 넣음
        .addInterceptor(loggingInterceptor) // 통신 로그 출력
        .connectTimeout(30, TimeUnit.SECONDS)
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // 우리가 만든 인터페이스를 서비스 객체로 구현
    val reqResService: ReqResService = retrofit.create(ReqResService::class.java)
}