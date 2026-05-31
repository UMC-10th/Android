package com.example.nikecompose.data.api

import com.example.nikecompose.data.dto.response.UserListResponse
import com.example.nikecompose.data.dto.response.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ReqresApiService {

    @GET("api/users/{userId}")
    suspend fun getUser(
        @Header("x-api-key") apiKey: String,
        @Path("userId") userId: Int
    ): Response<UserResponse>

    @GET("api/users")
    suspend fun getUsers(
        @Header("x-api-key") apiKey: String,
        @Query("page") page: Int
    ): Response<UserListResponse>
}