package com.senemyalin.jetpackcomposetraining.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.senemyalin.jetpackcomposetraining.presentation.screens.category.navigation.categoryScreen
import com.senemyalin.jetpackcomposetraining.presentation.screens.categorycontent.navigation.categoryContentScreen
import com.senemyalin.jetpackcomposetraining.presentation.screens.categorycontent.navigation.navigateToCategoryContent
import com.senemyalin.jetpackcomposetraining.presentation.screens.detail.navigation.detailScreen
import com.senemyalin.jetpackcomposetraining.presentation.screens.detail.navigation.navigateToDetail
import com.senemyalin.jetpackcomposetraining.presentation.screens.randommeal.navigation.randomMealScreen
import com.senemyalin.jetpackcomposetraining.presentation.screens.search.navigation.searchScreen

@Composable
fun BottomNavGraph(navController: NavHostController, innerPadding: PaddingValues) {

    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.RandomMeal.route,
        Modifier.padding(innerPadding),
    ) {
        randomMealScreen { navController.navigateToDetail(it) }
        categoryContentScreen(
            navigateToBack = { navController.navigateUp() },
            navigateToDetail = { id -> navController.navigateToDetail(id) }
        )
        categoryScreen { categoryName -> navController.navigateToCategoryContent(categoryName) }
        searchScreen { navController.navigateToDetail(it) }
        detailScreen { navController.navigateUp() }
    }
}