package com.example.NikeApp.model

/**
 * 화면에서 사용하는 사용자 도메인 모델
 *  - [nickname] 은 ReqRes 의 first_name + last_name 을 합친 값
 *  - [avatarUrl] 은 프로필/팔로잉 이미지로 사용
 */
data class User(
    val id: Int,
    val nickname: String,
    val avatarUrl: String,
)
