package com.example.nike

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ReqresApiService {

    // 1번 유저 정보 가져오기
    @GET("api/users/{id}")
    suspend fun getUser(
        @Header("x-api-key") apiKey: String,
        @Path("id") userId: Int
    ): Response<UserResponse>

    // 유저 리스트 가져오기
    @GET("api/users")
    suspend fun getUsers(
        @Header("x-api-key") apiKey: String,
        @Query("page") page: Int
    ): Response<UserListResponse>
}