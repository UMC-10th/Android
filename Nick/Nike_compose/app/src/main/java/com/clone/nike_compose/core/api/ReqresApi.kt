package com.clone.nike_compose.core.api

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

data class ReqresUsersResponse(
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int,
    val data: List<UserDto>
)

data class UserDto(
    val id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: String
)
interface ReqresApi {
    @GET("api/users")
    suspend fun getUsers(
        @Query("page") page: Int = 1,
        @Header("x-api-key") apiKey: String
    ): ReqresUsersResponse
}
