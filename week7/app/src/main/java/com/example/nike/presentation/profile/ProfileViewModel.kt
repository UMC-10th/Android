package com.example.nike.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nike.data.remote.repository.ProfileRepository
import com.example.nike.domain.model.profile.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    private val profileRepository = ProfileRepository()

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users.asStateFlow()

    init {
        getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch {
            runCatching {
                profileRepository.getUsers()
            }.onSuccess { users ->
                _users.value = users
            }.onFailure {
                it.printStackTrace()
            }
        }
    }
}