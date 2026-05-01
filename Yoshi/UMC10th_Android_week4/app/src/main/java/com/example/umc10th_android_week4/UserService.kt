package com.example.umc10th_android_week4

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface UserService {

    // 1번 유저 정보 가져오기
    @GET("api/users/{id}")
    suspend fun getUser(
        @Header("x-api-key") apiKey: String,
        @Path("id") userId: Int
    ): UserResponse

    // 유저 목록 가져오기 (팔로잉 리스트)
    @GET("api/users")
    suspend fun getUsers(
        @Header("x-api-key") apiKey: String
    ): UserListResponse
}