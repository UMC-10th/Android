package com.example.NikeApp.data.remote.dto

import com.example.NikeApp.model.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * ReqRes API 의 사용자 한 명을 표현하는 DTO
 * 예) GET https://reqres.in/api/users/1 -> data 필드
 */
@Serializable
data class UserDto(
    val id: Int,
    val email: String,
    @SerialName("first_name") val firstName: String,
    @SerialName("last_name") val lastName: String,
    val avatar: String,
)

/** GET /api/users/{id} 응답 */
@Serializable
data class SingleUserResponse(
    val data: UserDto,
)

/** GET /api/users?page= 응답 */
@Serializable
data class UserListResponse(
    val page: Int,
    @SerialName("per_page") val perPage: Int,
    val total: Int,
    @SerialName("total_pages") val totalPages: Int,
    val data: List<UserDto>,
)

/** DTO -> 도메인 모델 변환 (닉네임 = first_name + last_name) */
fun UserDto.toUser(): User = User(
    id = id,
    nickname = "$firstName $lastName",
    avatarUrl = avatar,
)
