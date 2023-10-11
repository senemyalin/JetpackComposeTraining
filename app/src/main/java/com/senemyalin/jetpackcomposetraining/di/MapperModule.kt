package com.senemyalin.jetpackcomposetraining.di

import com.senemyalin.jetpackcomposetraining.common.ListMapper
import com.senemyalin.jetpackcomposetraining.data.model.Meal
import com.senemyalin.jetpackcomposetraining.data.repository.mapper.DataListMapperImpl
import com.senemyalin.jetpackcomposetraining.data.repository.model.MealEntity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class MapperModule{

    @Binds
    @ViewModelScoped
    abstract fun bindDataMapper(dataListMapperImpl: DataListMapperImpl): ListMapper<Meal, MealEntity>
}