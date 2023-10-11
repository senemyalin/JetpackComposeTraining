package com.senemyalin.jetpackcomposetraining.presentation.screens.category.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.senemyalin.jetpackcomposetraining.navigation.BottomBarScreen
import com.senemyalin.jetpackcomposetraining.presentation.screens.category.CategoryScreen

const val categoryNavigationRoute = "category_route"

fun NavController.navigateToCategory(
    navOptions: NavOptions? = null
) {
    this.navigate(categoryNavigationRoute, navOptions)
}

fun NavGraphBuilder.categoryScreen(onItemClick: (String?) -> Unit) {
    composable(BottomBarScreen.Category.route) {
        CategoryScreen(
            hiltViewModel(),
            onItemClick = {
                onItemClick.invoke(it)
            },
        )
    }
}