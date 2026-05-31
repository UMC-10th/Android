package com.clone.nike_compose.core.repository

import com.clone.nike_compose.core.api.ReqresApi
import com.clone.nike_compose.core.api.UserDto

class ProfileRepository(
    private val api: ReqresApi
) {
    suspend fun loadUsers(): List<UserDto> {
        return api.getUsers(
            page = 1,
            apiKey = "reqres_08962a9022be4a3499d4dbe5e1bbc482"
        ).data
    }
}