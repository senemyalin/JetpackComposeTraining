package com.senemyalin.jetpackcomposetraining.domain.usecase

import com.senemyalin.jetpackcomposetraining.common.NetworkResponse
import com.senemyalin.jetpackcomposetraining.data.repository.model.MealEntity
import com.senemyalin.jetpackcomposetraining.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchMealUseCaseImpl @Inject constructor(
    private val mealRepository: MealRepository
) : SearchMealUseCase {

    override fun invoke(searchKey: String): Flow<NetworkResponse<List<MealEntity>>> =
        mealRepository.searchMeal(searchKey)

}