package com.senemyalin.jetpackcomposetraining.domain.usecase

import com.senemyalin.jetpackcomposetraining.common.NetworkResponse
import com.senemyalin.jetpackcomposetraining.data.model.FilteredMeal
import kotlinx.coroutines.flow.Flow

interface FilterMealByCategoryUseCase {
    operator fun invoke(category: String): Flow<NetworkResponse<List<FilteredMeal>>>

}