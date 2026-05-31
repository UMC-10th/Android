package com.example.nikecompose.feature.profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nikecompose.data.repository.ProfileRepository
import com.example.nikecompose.feature.profile.presentation.model.ProfileUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    private val repository = ProfileRepository()

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        loadProfile()
    }

    private fun loadProfile() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                errorMessage = null
            )

            val profileResult = repository.getProfileUser()
            val followingResult = repository.getFollowingUsers()

            val profileUser = profileResult.getOrNull()
            val followingUsers = followingResult.getOrNull().orEmpty()

            val errorMessage = when {
                profileResult.isFailure -> profileResult.exceptionOrNull()?.message
                followingResult.isFailure -> followingResult.exceptionOrNull()?.message
                else -> null
            }

            _uiState.value = ProfileUiState(
                isLoading = false,
                profileUser = profileUser,
                followingUsers = followingUsers,
                errorMessage = errorMessage
            )
        }
    }
}