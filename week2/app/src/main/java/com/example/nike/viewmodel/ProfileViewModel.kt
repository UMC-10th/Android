package com.example.nike.viewmodel

import android.R.attr.apiKey
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.nike.data.dto.ProfileResponse
import com.example.nike.data.repository.ProfileRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: ProfileRepository) : ViewModel() {

    private val _profileResult = MutableLiveData<Result<ProfileResponse>>()
    val profileResult: LiveData<Result<ProfileResponse>> = _profileResult

    private val _userListResult = MutableLiveData<Result<List<ProfileResponse>>>()
    val userListResult: LiveData<Result<List<ProfileResponse>>> = _userListResult

    fun fetchProfile(apiKey: String) {
        viewModelScope.launch {
            val result = repository.getUserProfile(apiKey)
            _profileResult.postValue(result)
        }
    }

    fun fetchUserList(apiKey: String) {
        viewModelScope.launch {
            val result = repository.getUserList(apiKey)
            _userListResult.postValue(result)
        }
    }
}

class ProfileViewModelFactory(private val repository: ProfileRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProfileViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}