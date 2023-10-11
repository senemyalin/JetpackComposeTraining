package com.senemyalin.jetpackcomposetraining.presentation.screens.category.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.senemyalin.jetpackcomposetraining.navigation.BottomBarScreen
import com.senemyalin.jetpackcomposetraining.presentation.CategoryUiState
import com.senemyalin.jetpackcomposetraining.presentation.screens.category.CategoryScreen
import com.senemyalin.jetpackcomposetraining.presentation.screens.category.CategoryViewModel

const val categoryNavigationRoute = "category_route"

fun NavController.navigateToCategory(
    navOptions: NavOptions? = null
) {
    this.navigate(categoryNavigationRoute, navOptions)
}

fun NavGraphBuilder.categoryScreen(onItemClick: (String?) -> Unit) {
    composable(BottomBarScreen.Category.route) {
        val viewModel: CategoryViewModel = hiltViewModel()
        val viewState: CategoryUiState by viewModel.categories.observeAsState(CategoryUiState.Idle)

        LaunchedEffect(key1 = true) {
            viewModel.getCategories()
        }

        CategoryScreen(
            viewState = viewState,
            onItemClick = {
                onItemClick.invoke(it)
            },
        )
    }
}