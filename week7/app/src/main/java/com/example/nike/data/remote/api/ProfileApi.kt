package com.example.nike.data.remote.api

import com.example.nike.data.remote.dto.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ProfileApi {
    @GET("users")
    suspend fun getUsers(
        @Query("page") page: Int = 1,
    ): UserResponse
}