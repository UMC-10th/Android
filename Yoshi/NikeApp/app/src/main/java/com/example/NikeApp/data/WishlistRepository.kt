package com.example.NikeApp.data

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

/**
 * 위시리스트 ID 집합 저장소
 * 앱이 종료/재실행되어도 데이터가 유지
 * Compose에서 즉시 반응할 수 있게 [State] 사용
 */
class WishlistRepository(context: Context) {

    private val prefs = context.applicationContext
        .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    private val _wishlistIds = mutableStateOf<Set<String>>(loadFromPrefs())

    /** 현재 위시리스트에 담긴 상품 ID 집합 */
    val wishlistIds: State<Set<String>> = _wishlistIds

    /**
     * 하트 on/off
     * 이미 들어있으면 제거 없으면 추가
     */
    fun toggle(productId: String) {
        val current = _wishlistIds.value
        val updated = if (productId in current) current - productId else current + productId
        _wishlistIds.value = updated
        saveToPrefs(updated)
    }

    fun isWished(productId: String): Boolean = productId in _wishlistIds.value

    private fun loadFromPrefs(): Set<String> =
        prefs.getStringSet(KEY_IDS, emptySet())?.toSet() ?: emptySet()

    private fun saveToPrefs(ids: Set<String>) {
        prefs.edit().putStringSet(KEY_IDS, ids).apply()
    }

    companion object {
        private const val PREFS_NAME = "wishlist_prefs"
        private const val KEY_IDS = "wishlist_ids"
    }
}
