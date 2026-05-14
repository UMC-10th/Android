package com.example.nike

interface RemoteRepository {
    suspend fun getUser(userId: Int): Result<ReqresUser>
    suspend fun getUsers(page: Int): Result<List<ReqresUser>>
}