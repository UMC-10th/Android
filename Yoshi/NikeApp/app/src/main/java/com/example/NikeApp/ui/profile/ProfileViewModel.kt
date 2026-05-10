package com.example.NikeApp.ui.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.NikeApp.data.model.UserData
import com.example.NikeApp.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProfileUiState(
    val user: UserData? = null,
    val followings: List<UserData> = emptyList(),
    val errorMessage: String? = null
)

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        loadUser()
        loadFollowings()
    }

    private fun loadUser() {
        viewModelScope.launch {
            try {
                val user = userRepository.getUser(userId = 1)
                _uiState.update { it.copy(user = user) }
                Log.d(TAG, "유저 정보 로드 성공: ${user.firstName}")
            } catch (e: Exception) {
                Log.e(TAG, "유저 정보 로드 실패: ${e.message}")
                _uiState.update { it.copy(errorMessage = "유저 정보 로드 실패") }
            }
        }
    }

    private fun loadFollowings() {
        viewModelScope.launch {
            try {
                val list = userRepository.getUsers()
                _uiState.update { it.copy(followings = list) }
                Log.d(TAG, "팔로잉 리스트 로드 성공: ${list.size}명")
            } catch (e: Exception) {
                Log.e(TAG, "팔로잉 리스트 로드 실패: ${e.message}")
                _uiState.update { it.copy(errorMessage = "팔로잉 리스트 로드 실패") }
            }
        }
    }

    // 에러 메시지 1회 소비 후 초기화 (Toast 노출 방지)
    fun consumeErrorMessage() {
        _uiState.update { it.copy(errorMessage = null) }
    }

    companion object {
        private const val TAG = "ProfileViewModel"
    }
}
