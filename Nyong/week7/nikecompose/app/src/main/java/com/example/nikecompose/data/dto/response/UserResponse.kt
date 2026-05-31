package com.example.nikecompose.data.dto.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    val data: ReqresUserDto
)

data class ReqresUserDto(
    val id: Int,
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    val avatar: String
)