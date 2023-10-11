package com.senemyalin.jetpackcomposetraining.domain.usecase

import com.senemyalin.jetpackcomposetraining.common.NetworkResponse
import com.senemyalin.jetpackcomposetraining.data.repository.model.MealEntity
import kotlinx.coroutines.flow.Flow

interface GetMealDetailsByIdUseCase {
    operator fun invoke(id: String): Flow<NetworkResponse<List<MealEntity>>>

}