package com.senemyalin.jetpackcomposetraining.di

import com.senemyalin.jetpackcomposetraining.domain.usecase.FilterMealByCategoryUseCase
import com.senemyalin.jetpackcomposetraining.domain.usecase.FilterMealByCategoryUseCaseImpl
import com.senemyalin.jetpackcomposetraining.domain.usecase.GetCategoriesUseCase
import com.senemyalin.jetpackcomposetraining.domain.usecase.GetCategoriesUseCaseImpl
import com.senemyalin.jetpackcomposetraining.domain.usecase.GetMealDetailsByIdUseCase
import com.senemyalin.jetpackcomposetraining.domain.usecase.GetMealDetailsByIdUseCaseImpl
import com.senemyalin.jetpackcomposetraining.domain.usecase.GetRandomMealUseCase
import com.senemyalin.jetpackcomposetraining.domain.usecase.GetRandomMealUseCaseImpl
import com.senemyalin.jetpackcomposetraining.domain.usecase.SearchMealUseCase
import com.senemyalin.jetpackcomposetraining.domain.usecase.SearchMealUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    @ViewModelScoped
    abstract fun bindGetRandomMealUseCase(getRandomMealUseCaseImpl: GetRandomMealUseCaseImpl): GetRandomMealUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindSearchMealUseCase(searchMealUseCaseImpl: SearchMealUseCaseImpl): SearchMealUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindFilterMealByCategoryUseCase(filterMealByCategoryUseCaseImpl: FilterMealByCategoryUseCaseImpl): FilterMealByCategoryUseCase


    @Binds
    @ViewModelScoped
    abstract fun bindGetCategoriesUseCase(getCategoriesUseCaseImpl: GetCategoriesUseCaseImpl): GetCategoriesUseCase


    @Binds
    @ViewModelScoped
    abstract fun bindGetMealDetailsByIdUseCase(getMealDetailsByIdUseCaseImpl: GetMealDetailsByIdUseCaseImpl): GetMealDetailsByIdUseCase
}