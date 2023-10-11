package com.senemyalin.jetpackcomposetraining.presentation.screens.detail.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.google.gson.Gson
import com.senemyalin.jetpackcomposetraining.common.Extensions.toJson
import com.senemyalin.jetpackcomposetraining.presentation.screens.detail.DetailScreen

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
        DetailScreen(
            hiltViewModel(),
            navigateToBack = {
                navigateToBack.invoke()
            })
    }
}