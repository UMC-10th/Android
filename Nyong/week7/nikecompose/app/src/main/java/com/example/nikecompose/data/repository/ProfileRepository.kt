package com.example.nikecompose.data.repository

import com.example.nikecompose.BuildConfig
import com.example.nikecompose.data.api.ReqresClient
import com.example.nikecompose.data.dto.response.ReqresUserDto

class ProfileRepository {

    private val apiService = ReqresClient.apiService
    private val apiKey = BuildConfig.REQRES_API_KEY

    suspend fun getProfileUser(): Result<ReqresUserDto> {
        return try {
            val response = apiService.getUser(
                apiKey = apiKey,
                userId = 1
            )

            if (response.isSuccessful) {
                val user = response.body()?.data

                if (user != null) {
                    Result.success(user)
                } else {
                    Result.failure(Exception("유저 정보가 비어 있습니다."))
                }
            } else {
                Result.failure(Exception("프로필 요청 실패: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getFollowingUsers(): Result<List<ReqresUserDto>> {
        return try {
            val response = apiService.getUsers(
                apiKey = apiKey,
                page = 1
            )

            if (response.isSuccessful) {
                val users = response.body()?.data.orEmpty()
                Result.success(users.take(6))
            } else {
                Result.failure(Exception("팔로잉 목록 요청 실패: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}