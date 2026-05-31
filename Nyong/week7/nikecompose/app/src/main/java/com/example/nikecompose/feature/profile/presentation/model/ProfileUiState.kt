package com.example.nikecompose.feature.profile.presentation.model

import com.example.nikecompose.data.dto.response.ReqresUserDto

data class ProfileUiState(
    val isLoading: Boolean = false,
    val profileUser: ReqresUserDto? = null,
    val followingUsers: List<ReqresUserDto> = emptyList(),
    val errorMessage: String? = null
)