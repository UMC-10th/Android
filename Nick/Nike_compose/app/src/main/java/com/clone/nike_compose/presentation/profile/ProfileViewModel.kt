package com.clone.nike_compose.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clone.nike_compose.core.api.NetworkModule
import com.clone.nike_compose.core.api.UserDto
import com.clone.nike_compose.core.repository.ProfileRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class ProfileUiState(
    val isLoading: Boolean = true,
    val profileUser: UserDto? = null,   // id=1
    val following: List<UserDto> = emptyList(),
    val error: String? = null
)

class ProfileViewModel : ViewModel() {
    private val repo = ProfileRepository(NetworkModule.api)

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState

    init {
        load()
    }

    private fun load() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            runCatching { repo.loadUsers() }
                .onSuccess { users ->
                    val p = users.firstOrNull { it.id == 1 }
                    val f = users.filter { it.id != 1 }
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            profileUser = p,
                            following = f
                        )
                    }
                }
                .onFailure { e ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = e.message ?: "불러오기 실패"
                        )
                    }
                }
        }
    }
}