package com.example.nike.data.repository

import com.example.nike.data.dto.ProfileResponse
import com.example.nike.data.service.ProfileService

class ProfileRepository(private val service: ProfileService) {

    suspend fun getUserProfile(apiKey: String): Result<ProfileResponse> = try {
        val response = service.getUserProfile(apiKey)

        if (response.isSuccessful) {
            val profileData = response.body()?.data

            if (profileData != null) {
                Result.success(profileData)
            } else {
                Result.failure(RuntimeException("데이터가 비어있습니다."))
            }
        } else {
            Result.failure(RuntimeException("오류 발생"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun getUserList(apiKey: String): Result<List<ProfileResponse>> = try {
        val response = service.getUserList(apiKey)

        if (response.isSuccessful) {
            val userList = response.body()?.data
            if (userList != null) {
                Result.success(userList)
            } else {
                Result.failure(RuntimeException("데이터가 비어있습니다."))
            }
        } else {
            Result.failure(RuntimeException("목록 조회 실패"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }
}