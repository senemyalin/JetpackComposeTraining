package com.senemyalin.jetpackcomposetraining.presentation.screens.categorycontent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.senemyalin.jetpackcomposetraining.data.model.FilteredMeal
import com.senemyalin.jetpackcomposetraining.presentation.FilteredMealUiState
import com.senemyalin.jetpackcomposetraining.presentation.components.CustomCircularProgressBar
import com.senemyalin.jetpackcomposetraining.presentation.components.GridItemView
import com.senemyalin.jetpackcomposetraining.presentation.components.TopBar
import com.senemyalin.jetpackcomposetraining.ui.theme.LightGreen

@Composable
fun CategoryContentScreen(
    viewModel: CategoryContentViewModel = viewModel(),
    navigateToBack: () -> Unit,
    navigateToDetail: (String?) -> Unit
) {
    val showProgressBarState = remember { mutableStateOf(false) }
    val showProgressBar by rememberUpdatedState(showProgressBarState.value)

    val viewState: FilteredMealUiState? by viewModel.categoryContent.observeAsState()

    viewModel.categoryName.value?.let {
        GetCategoryContentResult(
            viewState = viewState,
            showProgressBarState = showProgressBarState,
            categoryName = it,
            navigateToBack = navigateToBack,
            navigateToDetail = navigateToDetail
        )
    }
    if (showProgressBar) {
        CustomCircularProgressBar()
    }
}


@Composable
fun GetCategoryContentResult(
    viewState: FilteredMealUiState?,
    showProgressBarState: MutableState<Boolean>,
    categoryName: String = "",
    navigateToBack: () -> Unit,
    navigateToDetail: (String?) -> Unit
) {

    when (viewState) {
        is FilteredMealUiState.Error -> {
            showProgressBarState.value = true
        }

        FilteredMealUiState.Loading -> {
            showProgressBarState.value = true
        }

        is FilteredMealUiState.Success -> {
            showProgressBarState.value = true
            SetView(viewState.data, categoryName, navigateToBack, navigateToDetail)
            showProgressBarState.value = false
        }

        null -> {
            //DO NOTHING
        }
    }
}

@Composable
fun SetView(
    result: List<FilteredMeal>?,
    categoryName: String = "",
    navigateToBack: () -> Unit,
    navigateToDetail: (String?) -> Unit
) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        scaffoldState = scaffoldState,
        topBar = { TopBar(title = categoryName, isButtonShown = true, navigateToBack) },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .background(LightGreen),
                contentAlignment = Alignment.TopCenter
            ) {

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    if (result.isNullOrEmpty().not()) {
                        items(result!!) { meal ->
                            GridItemView(
                                id = meal.idMeal,
                                imageUrl = meal.strMealThumb,
                                imageHeight = 100.dp,
                                fontSize = 12.sp,
                                name = meal.strMeal,
                                onItemClick = navigateToDetail
                            )
                        }
                    }
                }

            }
        },
    )

}
