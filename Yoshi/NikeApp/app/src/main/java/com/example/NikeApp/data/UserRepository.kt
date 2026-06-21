package com.example.NikeApp.data

import com.example.NikeApp.data.remote.ReqResApi
import com.example.NikeApp.data.remote.ReqResClient
import com.example.NikeApp.data.remote.dto.toUser
import com.example.NikeApp.model.User

/**
 * 사용자 데이터 저장소.
 * 화면(Composable)은 이 Repository 의 suspend 함수만 호출하면 됩니다.
 */
class UserRepository(
    private val api: ReqResApi = ReqResClient.api,
) {
    /** 미션 요구사항: 내 정보(userId = 1) 가져오기 */
    suspend fun getMyProfile(): User = api.getUser(MY_USER_ID).data.toUser()

    /** 팔로잉 리스트 구성: 사용자 목록에서 나(1번)를 제외하고 가져오기 */
    suspend fun getFollowing(): List<User> =
        api.getUsers(page = 1).data
            .filter { it.id != MY_USER_ID }
            .map { it.toUser() }

    companion object {
        /** 워크북 미션 기준 내 userId 는 1번 */
        const val MY_USER_ID = 1
    }
}
