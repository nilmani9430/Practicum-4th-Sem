package com.example.agromart.di

import com.example.agromart.MainActivity
import com.example.agromart.repository.HomeRepository
import com.example.agromart.repository.impl.HomeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class, SingletonComponent::class)
abstract class HomeModule {
    @Binds
    abstract fun provideHomeRepository(impl: HomeRepositoryImpl): HomeRepository
}