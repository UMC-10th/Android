package com.example.nike.presentation.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.nike.core.data.dto.ProfileResponse
import com.example.nike.core.data.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository
) : ViewModel() {

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