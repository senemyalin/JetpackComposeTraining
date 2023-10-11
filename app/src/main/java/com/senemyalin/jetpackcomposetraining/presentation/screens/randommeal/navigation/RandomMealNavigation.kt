package com.senemyalin.jetpackcomposetraining.presentation.screens.randommeal.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.senemyalin.jetpackcomposetraining.navigation.BottomBarScreen
import com.senemyalin.jetpackcomposetraining.presentation.screens.randommeal.RandomMealScreen

const val randomMealNavigationRoute = "randommeal_route"

fun NavController.navigateRandomMeal(
    navOptions: NavOptions? = null
) {
    this.navigate(randomMealNavigationRoute, navOptions)
}

fun NavGraphBuilder.randomMealScreen(navigateToDetail: (String?) -> Unit) {
    composable(BottomBarScreen.RandomMeal.route) {
        RandomMealScreen(
            hiltViewModel(),
            navigateToDetail = {
                navigateToDetail.invoke(it)
            }
        )
    }
}