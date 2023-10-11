package com.senemyalin.jetpackcomposetraining.domain.usecase

import com.senemyalin.jetpackcomposetraining.common.NetworkResponse
import com.senemyalin.jetpackcomposetraining.data.model.Category
import com.senemyalin.jetpackcomposetraining.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesUseCaseImpl @Inject constructor(
    private val mealRepository: MealRepository
) : GetCategoriesUseCase {

    override fun invoke(): Flow<NetworkResponse<List<Category>>> = mealRepository.getCategories()

}