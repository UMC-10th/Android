package com.example.nike.domain.repository

import com.example.nike.data.model.UserData

interface UserRepository {
    suspend fun getUser(userId: Int): UserData?
    suspend fun getUserList(page: Int): List<UserData>
}