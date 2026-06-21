package com.example.composeapplication.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeapplication.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<ProfileUiState>(ProfileUiState.Loading)
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        fetchProfile()
    }

    fun fetchProfile() {
        viewModelScope.launch {
            _uiState.value = ProfileUiState.Loading
            try {
                val userResponse = RetrofitClient.service.getUser(1)
                val listResponse = RetrofitClient.service.getUserList(1)

                val user = userResponse.body()?.data
                val following = listResponse.body()?.data

                if (userResponse.isSuccessful && user != null && following != null) {
                    _uiState.value = ProfileUiState.Success(user, following)
                } else {
                    _uiState.value = ProfileUiState.Error("서버 오류 (${userResponse.code()})")
                }
            } catch (e: Exception) {
                _uiState.value = ProfileUiState.Error(e.message ?: "네트워크 연결을 확인해 주세요")
            }
        }
    }
}
