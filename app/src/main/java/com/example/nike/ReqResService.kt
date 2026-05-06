package com.example.nike

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ReqResService {
    // [미션 1] 특정 유저 1명 정보 가져오기
    // 사용 예: getUser(1)을 호출하면 "api/users/1"로 GET 요청이 갑니다.
    @GET("api/users/{id}")
    suspend fun getUser(
        @Path("id") userId: Int
    ): Response<SingleUserResponse>

    // [미션 2] 유저 리스트 가져오기
    // 사용 예: getUserList(1)을 호출하면 "api/users?page=1"로 GET 요청이 갑니다.
    @GET("api/users")
    suspend fun getUserList(
        @Query("page") page: Int
    ): Response<UserListResponse>
}