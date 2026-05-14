package com.example.nike.data.repository

import com.example.nike.data.model.UserData
import com.example.nike.data.remote.ReqResService
import com.example.nike.domain.repository.UserRepository
import javax.inject.Inject

class RemoteUserRepository @Inject constructor(
    private val service: ReqResService
) : UserRepository {

    override suspend fun getUser(userId: Int): UserData? {
        val response = service.getUser(userId)
        return if (response.isSuccessful) response.body()?.data else null
    }

    override suspend fun getUserList(page: Int): List<UserData> {
        val response = service.getUserList(page)
        return if (response.isSuccessful) response.body()?.data ?: emptyList() else emptyList()
    }
}