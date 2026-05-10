package com.example.nike

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val shoeDataStore: ShoeDataStore
) : LocalRepository {

    override fun getShopShoes(): Flow<ArrayList<ShoeData>> {
        return shoeDataStore.getShopShoes()
    }

    override fun getHomeShoes(): Flow<ArrayList<ShoeData>> {
        return shoeDataStore.getHomeShoes()
    }

    override fun getWishlist(): Flow<ArrayList<ShoeData>> {
        return shoeDataStore.getWishlist()
    }

    override suspend fun saveShopShoes(shoeList: ArrayList<ShoeData>) {
        shoeDataStore.saveShopShoes(shoeList)
    }

    override suspend fun saveHomeShoes(shoeList: ArrayList<ShoeData>) {
        shoeDataStore.saveHomeShoes(shoeList)
    }

    override suspend fun saveWishlist(wishlist: ArrayList<ShoeData>) {
        shoeDataStore.saveWishlist(wishlist)
    }
}