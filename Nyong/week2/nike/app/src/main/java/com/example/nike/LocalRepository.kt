package com.example.nike

import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    // 구매하기 상품 목록 가져오기
    fun getShopShoes(): Flow<ArrayList<ShoeData>>

    // 홈 상품 목록 가져오기
    fun getHomeShoes(): Flow<ArrayList<ShoeData>>

    // 위시리스트 상품 목록 가져오기
    fun getWishlist(): Flow<ArrayList<ShoeData>>

    // 구매하기 상품 목록 저장하기
    suspend fun saveShopShoes(shoeList: ArrayList<ShoeData>)

    // 홈 상품 목록 저장하기
    suspend fun saveHomeShoes(shoeList: ArrayList<ShoeData>)

    // 위시리스트 저장하기
    suspend fun saveWishlist(wishlist: ArrayList<ShoeData>)
}