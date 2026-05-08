package com.clone.nike.repository.remote_repository

import com.clone.nike.api.data.response.MyPageResponse

interface AuthRepository {
    suspend fun getFollowingProfile(page: Int, req: String): List<MyPageResponse>?
    suspend fun getUserProfile(page: Int, req: String): MyPageResponse?
}