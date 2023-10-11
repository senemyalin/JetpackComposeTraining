package com.senemyalin.jetpackcomposetraining.domain.repository

import com.senemyalin.jetpackcomposetraining.common.NetworkResponse
import com.senemyalin.jetpackcomposetraining.data.model.Category
import com.senemyalin.jetpackcomposetraining.data.model.FilteredMeal
import com.senemyalin.jetpackcomposetraining.data.model.Meal
import com.senemyalin.jetpackcomposetraining.data.repository.model.MealEntity
import kotlinx.coroutines.flow.Flow

interface MealRepository {

    fun getRandomMeal(): Flow<NetworkResponse<List<MealEntity>>>
    fun searchMeal(searchKey: String): Flow<NetworkResponse<List<MealEntity>>>
    fun getCategories(): Flow<NetworkResponse<List<Category>>>
    fun filterMealByCategory(category: String): Flow<NetworkResponse<List<FilteredMeal>>>
    fun getMealDetailsById(id: String): Flow<NetworkResponse<List<MealEntity>>>
}