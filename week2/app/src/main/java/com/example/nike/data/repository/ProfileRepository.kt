package com.example.nike.data.repository

import android.util.Log
import com.example.nike.data.dto.ProfileResponse
import com.example.nike.data.service.ProfileService

class ProfileRepository(private val service: ProfileService) {

    suspend fun getUserProfile(apiKey: String): Result<ProfileResponse> = try {
        val response = service.getUserProfile(apiKey)

        if (response.isSuccessful) {
            // 이제 response.body()는 ProfileBaseResponse 타입이므로 .data에 접근 가능합니다.
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

}