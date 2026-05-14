package com.example.nike.di

import com.example.nike.data.repository.LocalWishlistRepository
import com.example.nike.domain.repository.WishlistRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindWishlistRepository(impl: LocalWishlistRepository): WishlistRepository
}