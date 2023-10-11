package com.senemyalin.jetpackcomposetraining.presentation

import androidx.annotation.StringRes
import com.senemyalin.jetpackcomposetraining.data.model.Category
import com.senemyalin.jetpackcomposetraining.data.model.FilteredMeal
import com.senemyalin.jetpackcomposetraining.data.repository.model.MealEntity

sealed class MealUiState {
    object Loading : MealUiState()
    data class Success(val data: List<MealEntity>) : MealUiState()
    data class Error(@StringRes val message: Int) : MealUiState()
    object Idle : MealUiState()

}

sealed class FilteredMealUiState {
    object Loading : FilteredMealUiState()
    data class Success(val data: List<FilteredMeal>) : FilteredMealUiState()
    data class Error(@StringRes val message: Int) : FilteredMealUiState()
}

sealed class CategoryUiState {
    object Loading : CategoryUiState()
    data class Success(val data: List<Category>) : CategoryUiState()
    data class Error(@StringRes val message: Int) : CategoryUiState()
    object Idle : CategoryUiState()
}