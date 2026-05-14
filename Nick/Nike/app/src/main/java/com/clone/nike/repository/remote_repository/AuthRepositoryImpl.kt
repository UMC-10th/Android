package com.clone.nike.repository.repository

import android.util.Log
import com.clone.nike.api.data.response.MyPageResponse
import com.clone.nike.api.DTO.AuthService
import com.clone.nike.repository.remote_repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val service: AuthService): AuthRepository {
    override suspend fun getFollowingProfile(page: Int, req: String): List<MyPageResponse>? {
        val res = service.getMyPageData(page,req)
        if (res.isSuccessful) {
            return res.body()?.data
        } else {
            Log.d("Error_Message", res.message())
        }
        return null
    }
    override suspend fun getUserProfile(page: Int, req: String): MyPageResponse? {
        val list = getFollowingProfile(page, req)
        val userProfile = list?.find { it.id == 1 }

        return userProfile
    }
}