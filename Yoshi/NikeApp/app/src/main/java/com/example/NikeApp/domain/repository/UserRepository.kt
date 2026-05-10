package com.example.NikeApp.domain.repository

import com.example.NikeApp.data.model.UserData

// 유저 데이터(Remote API) 접근
interface UserRepository {
    suspend fun getUser(userId: Int): UserData
    suspend fun getUsers(): List<UserData>
}
