package com.clone.nike.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.clone.nike.api.data.response.MyPageResponse
import com.clone.nike.repository.repository.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: AuthRepository): ViewModel() {
    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _profileImg = MutableLiveData<String>()
    val profileImg: LiveData<String> = _profileImg

    private val _followingImgs = MutableLiveData<List<String>>()
    val followingImgs: LiveData<List<String>> = _followingImgs

    fun loadProfile(page: Int, req: String) {
        viewModelScope.launch {
            val res = repository.getUserProfile(page,req)

            val name = res?.let { "${it.firstName} ${it.lastName}" }
            val img = res?.avatar

            _name.value = name ?: "unknown"
            _profileImg.value = img ?: "unknown"
        }
    }

    fun loadFollowing(page: Int, req: String) {
        viewModelScope.launch {
            val res = repository.getFollowingProfile(page,req)

            _followingImgs.value = res?.map { it.avatar }
        }
    }
}

class AuthViewModelFactory(
    private val repository: AuthRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthViewModel(repository) as T
    }
}