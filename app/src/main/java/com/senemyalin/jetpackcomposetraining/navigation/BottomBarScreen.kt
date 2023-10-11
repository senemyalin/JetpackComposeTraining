package com.senemyalin.jetpackcomposetraining.navigation

import androidx.annotation.DrawableRes
import com.senemyalin.jetpackcomposetraining.R
import com.senemyalin.jetpackcomposetraining.presentation.screens.category.navigation.categoryNavigationRoute
import com.senemyalin.jetpackcomposetraining.presentation.screens.randommeal.navigation.randomMealNavigationRoute
import com.senemyalin.jetpackcomposetraining.presentation.screens.search.navigation.searchNavigationRoute

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    @DrawableRes val icon: Int
) {
    object Category : BottomBarScreen(
        route = categoryNavigationRoute,
        title = "Category",
        icon = R.drawable.icon_category
    )

    object RandomMeal : BottomBarScreen(
        route = randomMealNavigationRoute,
        title = "Random Meal",
        icon = R.drawable.icon_randommeal
    )

    object Search : BottomBarScreen(
        route = searchNavigationRoute,
        title = "Search",
        icon = R.drawable.icon_search
    )
}