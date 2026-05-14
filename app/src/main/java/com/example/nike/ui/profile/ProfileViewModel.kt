package com.example.nike.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nike.data.model.UserData
import com.example.nike.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private val _user = MutableStateFlow<UserData?>(null)
    val user: StateFlow<UserData?> = _user.asStateFlow()

    private val _followingList = MutableStateFlow<List<UserData>>(emptyList())
    val followingList: StateFlow<List<UserData>> = _followingList.asStateFlow()

    init {
        loadUserProfile()
        loadFollowingList()
    }

    private fun loadUserProfile() {
        viewModelScope.launch {
            _user.value = repository.getUser(1)
        }
    }

    private fun loadFollowingList() {
        viewModelScope.launch {
            _followingList.value = repository.getUserList(1)
        }
    }
}