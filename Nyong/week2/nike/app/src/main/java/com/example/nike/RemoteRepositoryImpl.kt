package com.example.nike

import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val apiService: ReqresApiService
) : RemoteRepository {

    override suspend fun getUser(userId: Int): Result<ReqresUser> {
        return try {
            val response = apiService.getUser(
                apiKey = BuildConfig.REQRES_API_KEY,
                userId = userId
            )

            if (response.isSuccessful) {
                val user = response.body()?.data

                if (user != null) {
                    Result.success(user)
                } else {
                    Result.failure(Exception("유저 정보가 비어 있습니다."))
                }
            } else {
                Result.failure(Exception("유저 조회 실패: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getUsers(page: Int): Result<List<ReqresUser>> {
        return try {
            val response = apiService.getUsers(
                apiKey = BuildConfig.REQRES_API_KEY,
                page = page
            )

            if (response.isSuccessful) {
                val users = response.body()?.data ?: emptyList()
                Result.success(users)
            } else {
                Result.failure(Exception("유저 목록 조회 실패: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}