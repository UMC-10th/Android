package com.clone.nike.api.data.response

import com.google.gson.annotations.SerializedName

data class AuthResponse<T>(
    val page: Int,

    @SerializedName("per_page")
    val perPage: Int,

    val total: Int,

    @SerializedName("total_pages")
    val totalPages: Int,

    val data: T?
)

data class MyPageResponse(
    val id: Int,
    val email: String,

    @SerializedName("first_name")
    val firstName: String,

    @SerializedName("last_name")
    val lastName: String,

    val avatar: String
)


