package com.example.nike.core

import android.content.Context
import com.example.nike.core.data.PreferenceManager
import com.example.nike.core.data.datasource.local.LocalDataSource
import com.example.nike.core.data.datasource.remote.ProfileRemoteDataSource
import com.example.nike.core.data.network.ApiClient
import com.example.nike.core.data.repository.ProfileRepository
import com.example.nike.core.data.service.ProfileService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePreferenceManager(
        @ApplicationContext context: Context
    ): PreferenceManager = PreferenceManager(context)

    @Provides @Singleton
    fun provideLocalDataSource(
        preferenceManager: PreferenceManager
    ): LocalDataSource = LocalDataSource(preferenceManager)

    @Provides @Singleton
    fun provideRetrofit(): Retrofit = ApiClient.retrofit

    @Provides @Singleton
    fun provideProfileService(retrofit: Retrofit): ProfileService =
        retrofit.create(ProfileService::class.java)

    @Provides @Singleton
    fun provideProfileRemoteDataSource(
        service: ProfileService
    ): ProfileRemoteDataSource = ProfileRemoteDataSource(service)

    @Provides @Singleton
    fun provideProfileRepository(
        remoteDataSource: ProfileRemoteDataSource
    ): ProfileRepository = ProfileRepository(remoteDataSource)
}