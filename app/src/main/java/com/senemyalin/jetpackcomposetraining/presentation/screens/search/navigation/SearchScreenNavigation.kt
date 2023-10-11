package com.senemyalin.jetpackcomposetraining.presentation.screens.search.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.senemyalin.jetpackcomposetraining.presentation.screens.search.SearchScreen

const val searchNavigationRoute = "search_route"

fun NavController.navigateToSearch(
    navOptions: NavOptions? = null
) {
    this.navigate(searchNavigationRoute, navOptions)
}

fun NavGraphBuilder.searchScreen(navigateToDetail: (String?) -> Unit) {
    composable(searchNavigationRoute) {
        SearchScreen(
            hiltViewModel(),
            navigateToDetail = {
                navigateToDetail.invoke(it)
            }
        )
    }
}