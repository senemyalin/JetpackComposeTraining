package com.senemyalin.jetpackcomposetraining.data.source

import com.senemyalin.jetpackcomposetraining.common.NetworkResponse
import com.senemyalin.jetpackcomposetraining.data.api.MealApi
import com.senemyalin.jetpackcomposetraining.data.model.Category
import com.senemyalin.jetpackcomposetraining.data.model.FilteredMeal
import com.senemyalin.jetpackcomposetraining.data.model.Meal
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val mealApi: MealApi
) : RemoteDataSource {

    override suspend fun getRandomMeal(): NetworkResponse<List<Meal>> =
        try {
            val response = mealApi.getRandomMeal()
            NetworkResponse.Success(response.meals)
        } catch (e: Exception) {
            NetworkResponse.Error(e)
        }

    override suspend fun searchMeal(searchKey: String): NetworkResponse<List<Meal>> =
        try {
            val response = mealApi.searchMeal(searchKey)
            NetworkResponse.Success(response.meals)
        } catch (e: Exception) {
            NetworkResponse.Error(e)
        }

    override suspend fun getCategories(): NetworkResponse<List<Category>> =
        try {
            val response = mealApi.getCategories()
            NetworkResponse.Success(response.categories)
        } catch (e: Exception) {
            NetworkResponse.Error(e)
        }


    override suspend fun filterMealByCategory(category: String): NetworkResponse<List<FilteredMeal>> =
        try {
            val response = mealApi.filterMealByCategory(category)
            NetworkResponse.Success(response.meals)
        } catch (e: Exception) {
            NetworkResponse.Error(e)
        }


    override suspend fun getMealDetailsById(id: String): NetworkResponse<List<Meal>> =
        try {
            val response = mealApi.getMealDetailsById(id)
            NetworkResponse.Success(response.meals)
        } catch (e: Exception) {
            NetworkResponse.Error(e)
        }

}