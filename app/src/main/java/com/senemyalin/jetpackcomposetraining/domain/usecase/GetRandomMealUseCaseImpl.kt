package com.senemyalin.jetpackcomposetraining.domain.usecase

import com.senemyalin.jetpackcomposetraining.common.NetworkResponse
import com.senemyalin.jetpackcomposetraining.data.repository.model.MealEntity
import com.senemyalin.jetpackcomposetraining.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRandomMealUseCaseImpl @Inject constructor(
    private val mealRepository: MealRepository
): GetRandomMealUseCase {

    override fun invoke(): Flow<NetworkResponse<List<MealEntity>>> = mealRepository.getRandomMeal()

}