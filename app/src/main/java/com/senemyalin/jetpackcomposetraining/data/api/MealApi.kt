package com.senemyalin.jetpackcomposetraining.data.api

import com.senemyalin.jetpackcomposetraining.data.model.FilterByCategoryResponse
import com.senemyalin.jetpackcomposetraining.data.model.GetCategoryResponse
import com.senemyalin.jetpackcomposetraining.data.model.GetMealDetailsByIdResponse
import com.senemyalin.jetpackcomposetraining.data.model.GetRandomMealResponse
import com.senemyalin.jetpackcomposetraining.data.model.SearchMealResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    @GET("random.php")
    suspend fun getRandomMeal(): GetRandomMealResponse

    @GET("search.php")
    suspend fun searchMeal(@Query("s") searchKey: String): SearchMealResponse

    @GET("categories.php")
    suspend fun getCategories(): GetCategoryResponse

    @GET("filter.php")
    suspend fun filterMealByCategory(@Query("c") category: String): FilterByCategoryResponse

    @GET("lookup.php")
    suspend fun getMealDetailsById(@Query("i") id: String): GetMealDetailsByIdResponse
}