package com.clone.nike.repository

import com.clone.nike.repository.local_repository.DataStoreRepository
import com.clone.nike.repository.remote_repository.AuthRepository
import com.clone.nike.repository.repository.AuthRepositoryImpl
import com.clone.nike.repository.repository.DataStoreRepositoryImpl
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
    abstract fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindDataStoreRepository(impl: DataStoreRepositoryImpl): DataStoreRepository
}