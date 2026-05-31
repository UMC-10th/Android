package com.example.nike.data.remote.repository

import com.example.nike.data.remote.RetrofitClient
import com.example.nike.domain.model.profile.User

class ProfileRepository {
    private val profileApi = RetrofitClient.profileApi

    suspend fun getUsers(): List<User> {
        return profileApi.getUsers().data
    }
}