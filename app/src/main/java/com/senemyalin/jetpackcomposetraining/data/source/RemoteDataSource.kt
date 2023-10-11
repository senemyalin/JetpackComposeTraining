package com.senemyalin.jetpackcomposetraining.data.source

import com.senemyalin.jetpackcomposetraining.common.NetworkResponse
import com.senemyalin.jetpackcomposetraining.data.model.Category
import com.senemyalin.jetpackcomposetraining.data.model.FilterByCategoryResponse
import com.senemyalin.jetpackcomposetraining.data.model.FilteredMeal
import com.senemyalin.jetpackcomposetraining.data.model.GetCategoryResponse
import com.senemyalin.jetpackcomposetraining.data.model.GetMealDetailsByIdResponse
import com.senemyalin.jetpackcomposetraining.data.model.Meal

interface RemoteDataSource {
    suspend fun getRandomMeal(): NetworkResponse<List<Meal>>
    suspend fun searchMeal(searchKey: String): NetworkResponse<List<Meal>>
    suspend fun getCategories(): NetworkResponse<List<Category>>
    suspend fun filterMealByCategory(category: String): NetworkResponse<List<FilteredMeal>>
    suspend fun getMealDetailsById(id: String): NetworkResponse<List<Meal>>

}