package com.example.composeapplication.data.model

import com.google.gson.annotations.SerializedName

// 서버에서 받아올 실제 유저 데이터
data class UserData(
    @SerializedName("id") val id: Int,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("avatar") val avatar: String
)

// 미션 1: 1번 유저 단일 정보를 받아올 때 쓰는 응답 객체
data class SingleUserResponse(
    @SerializedName("data") val data: UserData
)

// 미션 2: 팔로잉 리스트(여러 명)를 받아올 때 쓰는 응답 객체
data class UserListResponse(
    @SerializedName("data") val data: List<UserData>
)