package com.senemyalin.jetpackcomposetraining.domain.usecase

import com.senemyalin.jetpackcomposetraining.common.NetworkResponse
import com.senemyalin.jetpackcomposetraining.data.model.Meal
import com.senemyalin.jetpackcomposetraining.data.repository.model.MealEntity
import kotlinx.coroutines.flow.Flow

interface GetRandomMealUseCase {
    operator fun invoke(): Flow<NetworkResponse<List<MealEntity>>>
}