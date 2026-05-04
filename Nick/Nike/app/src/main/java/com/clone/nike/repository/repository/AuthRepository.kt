package com.clone.nike.repository.repository

import android.util.Log
import com.clone.nike.api.data.response.MyPageResponse
import com.clone.nike.api.DTO.AuthService

class AuthRepository(private val service: AuthService) {
    suspend fun getFollowingProfile(page: Int, req: String): List<MyPageResponse>? {
        val res = service.getMyPageData(page,req)
        if (res.isSuccessful) {
            return res.body()?.data
        } else {
            Log.d("Error_Message", res.message())
        }
        return null
    }
    suspend fun getUserProfile(page: Int, req: String): MyPageResponse? {
        val list = getFollowingProfile(page, req)
        val userProfile = list?.find { it.id == 1 }

        return userProfile
    }
}