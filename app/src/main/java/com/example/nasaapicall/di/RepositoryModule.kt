package com.example.nasaapicall.di

import com.example.nasaapicall.api.NasaApi
import com.example.nasaapicall.repository.ApiCallRepository
import com.example.nasaapicall.repository.ApiCallRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideNasaApi(
        nasaApi: NasaApi
    ) : ApiCallRepository{
        return ApiCallRepositoryImpl(nasaApi)
    }

}