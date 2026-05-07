package com.clone.nike.api.DTO

import com.clone.nike.api.data.response.AuthResponse
import com.clone.nike.api.data.response.MyPageResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthService {

    @GET("api/users")
    suspend fun getMyPageData(
        @Query("page") page: Int,
        @Header("x-api-key") apiKey: String
    ): Response<AuthResponse<List<MyPageResponse>>>
}