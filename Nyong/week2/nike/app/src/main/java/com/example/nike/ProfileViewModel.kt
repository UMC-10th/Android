package com.example.nike

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    fun loadProfile() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    errorMessage = null
                )
            }

            val userDeferred = async {
                remoteRepository.getUser(userId = 1)
            }

            val usersDeferred = async {
                remoteRepository.getUsers(page = 1)
            }

            val userResult = userDeferred.await()
            val usersResult = usersDeferred.await()

            if (userResult.isSuccess && usersResult.isSuccess) {
                val user = userResult.getOrNull()
                val users = usersResult.getOrNull().orEmpty()

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        user = user,
                        followingUsers = users.take(6),
                        errorMessage = null
                    )
                }
            } else {
                val message = userResult.exceptionOrNull()?.message
                    ?: usersResult.exceptionOrNull()?.message
                    ?: "프로필 정보를 불러오지 못했습니다."

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = message
                    )
                }
            }
        }
    }
}