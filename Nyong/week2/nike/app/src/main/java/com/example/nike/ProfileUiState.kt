package com.example.nike

data class ProfileUiState(
    val isLoading: Boolean = false,
    val user: ReqresUser? = null,
    val followingUsers: List<ReqresUser> = emptyList(),
    val errorMessage: String? = null
)