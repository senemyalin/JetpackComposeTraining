package com.senemyalin.jetpackcomposetraining.presentation.screens.categorycontent.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.google.gson.Gson
import com.senemyalin.jetpackcomposetraining.common.Extensions.toJson
import com.senemyalin.jetpackcomposetraining.presentation.screens.categorycontent.CategoryContentScreen

const val categoryContentNavigation = "category_content_route"

fun NavController.navigateToCategoryContent(
    categoryName: String?,
    navOptions: NavOptions? = null
) {
    this.navigate(categoryContentNavigation.plus("?categoryName=${categoryName.toJson()}"), navOptions)
}

fun NavGraphBuilder.categoryContentScreen(
    navigateToBack: () -> Unit,
    navigateToDetail: (String?) -> Unit
) {
    composable(
        categoryContentNavigation.plus("?categoryName={categoryName}")
    ) {
        CategoryContentScreen(
            hiltViewModel(),
            navigateToBack = {
                navigateToBack.invoke()
            },
            navigateToDetail = {
                navigateToDetail.invoke(it)
            })
    }
}