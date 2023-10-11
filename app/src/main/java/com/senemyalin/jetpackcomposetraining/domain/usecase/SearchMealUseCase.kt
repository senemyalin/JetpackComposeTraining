package com.senemyalin.jetpackcomposetraining.domain.usecase

import com.senemyalin.jetpackcomposetraining.common.NetworkResponse
import com.senemyalin.jetpackcomposetraining.data.repository.model.MealEntity
import kotlinx.coroutines.flow.Flow

interface SearchMealUseCase {
    operator fun invoke(searchKey: String): Flow<NetworkResponse<List<MealEntity>>>

}