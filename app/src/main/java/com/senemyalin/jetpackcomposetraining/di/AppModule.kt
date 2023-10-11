package com.senemyalin.jetpackcomposetraining.di

import com.senemyalin.jetpackcomposetraining.common.Constants
import com.senemyalin.jetpackcomposetraining.data.api.MealApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().apply {
            addConverterFactory(GsonConverterFactory.create())
            baseUrl(Constants.BASE_URL)
        }.build()
    }

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): MealApi {
        return retrofit.create(MealApi::class.java)
    }
}