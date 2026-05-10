package com.example.NikeApp.data.repository

import com.example.NikeApp.BuildConfig
import com.example.NikeApp.data.model.UserData
import com.example.NikeApp.data.remote.UserService
import com.example.NikeApp.domain.repository.UserRepository
import javax.inject.Inject

// Retrofit API 호출
class UserRepositoryImpl @Inject constructor(
    private val userService: UserService
) : UserRepository {

    override suspend fun getUser(userId: Int): UserData {
        val response = userService.getUser(
            apiKey = BuildConfig.NIKE_API_KEY,
            userId = userId
        )
        return response.data
    }

    override suspend fun getUsers(): List<UserData> {
        val response = userService.getUsers(
            apiKey = BuildConfig.NIKE_API_KEY
        )
        return response.data
    }
}
