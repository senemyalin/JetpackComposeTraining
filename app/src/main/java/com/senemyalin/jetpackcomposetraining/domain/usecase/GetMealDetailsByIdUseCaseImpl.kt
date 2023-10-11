package com.senemyalin.jetpackcomposetraining.domain.usecase

import com.senemyalin.jetpackcomposetraining.common.NetworkResponse
import com.senemyalin.jetpackcomposetraining.data.repository.model.MealEntity
import com.senemyalin.jetpackcomposetraining.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMealDetailsByIdUseCaseImpl @Inject constructor(
    private val mealRepository: MealRepository
) : GetMealDetailsByIdUseCase {

    override fun invoke(id: String): Flow<NetworkResponse<List<MealEntity>>> =
        mealRepository.getMealDetailsById(id)
}