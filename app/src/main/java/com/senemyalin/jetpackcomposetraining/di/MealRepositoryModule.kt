package com.senemyalin.jetpackcomposetraining.di

import com.senemyalin.jetpackcomposetraining.domain.repository.MealRepository
import com.senemyalin.jetpackcomposetraining.data.repository.MealRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class MealRepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindMealRepository(mealRepositoryImpl: MealRepositoryImpl): MealRepository
}