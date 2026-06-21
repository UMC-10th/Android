package com.example.composeapplication.ui.screens

import com.example.composeapplication.data.model.UserData

sealed interface ProfileUiState {
    data object Loading : ProfileUiState

    data class Success(
        val user: UserData,
        val followingList: List<UserData>
    ) : ProfileUiState

    data class Error(val message: String) : ProfileUiState
}
