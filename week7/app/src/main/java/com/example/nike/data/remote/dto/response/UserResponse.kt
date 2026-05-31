package com.example.nike.data.remote.dto.response

import com.example.nike.domain.model.profile.User
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val data: List<User>,
)