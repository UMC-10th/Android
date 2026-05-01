package com.example.umc10th_android_week4

import com.google.gson.annotations.SerializedName

// 단일 유저 응답용 (1번 유저 조회)
data class UserResponse(
    val data: UserData
)

// 유저 목록 응답용 (팔로잉 리스트)
data class UserListResponse(
    val data: List<UserData>
)

// 실제 유저 데이터
data class UserData(
    val id: Int,
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    val avatar: String
)