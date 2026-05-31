package com.example.NikeApp.data.remote

import com.example.NikeApp.data.remote.dto.SingleUserResponse
import com.example.NikeApp.data.remote.dto.UserListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * ReqRes(https://reqres.in) 사용자 API
 */
interface ReqResApi {

    /** 단일 사용자 조회 — 미션 요구사항: userId = 1 */
    @GET("api/users/{id}")
    suspend fun getUser(@Path("id") id: Int): SingleUserResponse

    /** 사용자 목록 조회 — 팔로잉 리스트 구성에 사용 */
    @GET("api/users")
    suspend fun getUsers(@Query("page") page: Int = 1): UserListResponse
}
