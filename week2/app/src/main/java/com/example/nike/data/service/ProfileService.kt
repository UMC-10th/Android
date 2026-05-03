package com.example.nike.data.service

import com.example.nike.data.dto.ProfileBaseResponse
import com.example.nike.data.dto.ProfileResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ProfileService {
    @GET("api/users/1")
    suspend fun getUserProfile(
        @Header("x-api-key") apiKey: String
    ): Response<ProfileBaseResponse>
}