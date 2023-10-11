package com.senemyalin.jetpackcomposetraining.presentation.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.senemyalin.jetpackcomposetraining.R
import com.senemyalin.jetpackcomposetraining.presentation.MealUiState
import com.senemyalin.jetpackcomposetraining.presentation.components.CustomCircularProgressBar
import com.senemyalin.jetpackcomposetraining.presentation.components.GridItemView
import com.senemyalin.jetpackcomposetraining.presentation.components.SearchBar
import com.senemyalin.jetpackcomposetraining.presentation.components.TopBar
import com.senemyalin.jetpackcomposetraining.ui.theme.LightGreen

@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    navigateToDetail: (String?) -> Unit,
) {
    val showProgressBarState = remember { mutableStateOf(false) }
    val showProgressBar by rememberUpdatedState(showProgressBarState.value)

    val scaffoldState = rememberScaffoldState()
    val viewState: MealUiState? by viewModel.searchMeal.observeAsState()


    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        scaffoldState = scaffoldState,
        topBar = { TopBar(title = stringResource(R.string.search_meal), isButtonShown = false) },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .background(LightGreen),
                contentAlignment = Alignment.TopCenter
            ) {
                Column {
                    SearchBar(onSearch = { query ->
                        searchMeal(viewModel, query)
                    })
                    GetSearchMealResults(viewState, showProgressBarState, navigateToDetail)
                }
            }
        },
    )

    if (showProgressBar) {
        CustomCircularProgressBar()
    }
}

@Composable
fun GetSearchMealResults(
    viewState: MealUiState?,
    showProgressBarState: MutableState<Boolean>,
    navigateToDetail: (String?) -> Unit
) {
    when (viewState) {
        is MealUiState.Error -> {
            showProgressBarState.value = false

        }

        MealUiState.Loading -> {
            showProgressBarState.value = true
        }

        is MealUiState.Success -> {
            showProgressBarState.value = true
            val result = viewState.data
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                if (result.isEmpty().not()) {
                    items(result) {
                        GridItemView(
                            id = it.id,
                            imageUrl = it.imageUrl,
                            imageHeight = 100.dp,
                            fontSize = 12.sp,
                            name = it.name,
                            onItemClick = navigateToDetail
                        )
                    }
                }
            }
            showProgressBarState.value = false
        }

        null -> {
            //DO NOTHING
        }

        MealUiState.Idle -> {
            //DO NOTHING
        }
    }
}


fun searchMeal(viewModel: SearchViewModel, searchKey: String) {
    viewModel.searchMeal(searchKey)
}