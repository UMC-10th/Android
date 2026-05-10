package com.example.NikeApp.di

import android.content.Context
import com.example.NikeApp.data.local.ProductStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Local 데이터 소스(DataStore 기반)를 만드는 방법을 Hilt에 전달
@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideProductStorage(
        @ApplicationContext context: Context
    ): ProductStorage = ProductStorage(context)
}
