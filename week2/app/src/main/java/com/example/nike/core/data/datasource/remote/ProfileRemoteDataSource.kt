package com.example.nike.core.data.datasource.remote

import com.example.nike.core.data.service.ProfileService
import javax.inject.Inject

class ProfileRemoteDataSource @Inject constructor(
    private val service: ProfileService
) {
    suspend fun getUserProfile(apiKey: String) = service.getUserProfile(apiKey)
    suspend fun getUserList(apiKey: String) = service.getUserList(apiKey)
}