package com.senemyalin.jetpackcomposetraining.presentation.screens.detail.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.senemyalin.jetpackcomposetraining.common.Extensions.toJson
import com.senemyalin.jetpackcomposetraining.presentation.MealUiState
import com.senemyalin.jetpackcomposetraining.presentation.screens.detail.DetailScreen
import com.senemyalin.jetpackcomposetraining.presentation.screens.detail.DetailViewModel

const val mealDetailNavigation = "meal_detail_route"

fun NavController.navigateToDetail(
    mealId: String?,
    navOptions: NavOptions? = null
) {
    this.navigate(mealDetailNavigation.plus("?mealId=${mealId.toJson()}"), navOptions)
}

fun NavGraphBuilder.detailScreen(navigateToBack: () -> Unit) {
    composable(
        mealDetailNavigation.plus("?mealId={mealId}")
    ) {
        val viewModel: DetailViewModel = hiltViewModel()
        val viewState: MealUiState by viewModel.details.observeAsState(MealUiState.Idle)

        LaunchedEffect(key1 = true ){
            viewModel.getMealDetails()
        }
        DetailScreen(
            viewState = viewState,
            navigateToBack = {
                navigateToBack.invoke()
            })
    }
}