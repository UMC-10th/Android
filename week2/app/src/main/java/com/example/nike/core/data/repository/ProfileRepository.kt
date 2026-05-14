package com.example.nike.core.data.repository

import com.example.nike.core.data.datasource.remote.ProfileRemoteDataSource
import com.example.nike.core.data.dto.ProfileResponse
import com.example.nike.core.data.service.ProfileService
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val remoteDataSource: ProfileRemoteDataSource
) {
    suspend fun getUserProfile(apiKey: String): Result<ProfileResponse> = try {
        val response = remoteDataSource.getUserProfile(apiKey)
        if (response.isSuccessful) {
            val data = response.body()?.data
            if (data != null) Result.success(data)
            else Result.failure(RuntimeException("데이터가 비어있습니다."))
        } else {
            Result.failure(RuntimeException("오류 발생"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun getUserList(apiKey: String): Result<List<ProfileResponse>> = try {
        val response = remoteDataSource.getUserList(apiKey)
        if (response.isSuccessful) {
            val data = response.body()?.data
            if (data != null) Result.success(data)
            else Result.failure(RuntimeException("데이터가 비어있습니다."))
        } else {
            Result.failure(RuntimeException("목록 조회 실패"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }
}