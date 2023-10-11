package com.senemyalin.jetpackcomposetraining.domain.usecase

import com.senemyalin.jetpackcomposetraining.common.NetworkResponse
import com.senemyalin.jetpackcomposetraining.data.model.FilteredMeal
import com.senemyalin.jetpackcomposetraining.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FilterMealByCategoryUseCaseImpl @Inject constructor(
    private val mealRepository: MealRepository
) : FilterMealByCategoryUseCase {

    override fun invoke(category: String): Flow<NetworkResponse<List<FilteredMeal>>> =
        mealRepository.filterMealByCategory(category)

}