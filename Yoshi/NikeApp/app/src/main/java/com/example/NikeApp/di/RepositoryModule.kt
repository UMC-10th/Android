package com.example.NikeApp.di

import com.example.NikeApp.data.repository.ProductRepositoryImpl
import com.example.NikeApp.data.repository.UserRepositoryImpl
import com.example.NikeApp.domain.repository.ProductRepository
import com.example.NikeApp.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Interface와 구현체를 연결
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProductRepository(
        impl: ProductRepositoryImpl
    ): ProductRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        impl: UserRepositoryImpl
    ): UserRepository
}
