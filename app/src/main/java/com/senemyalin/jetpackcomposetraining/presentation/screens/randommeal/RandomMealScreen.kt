package com.senemyalin.jetpackcomposetraining.presentation.screens.randommeal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.senemyalin.jetpackcomposetraining.presentation.components.TopBar
import com.senemyalin.jetpackcomposetraining.ui.theme.LightGreen

@Composable
fun RandomMealScreen(
    viewModel: RandomMealViewModel,
    navigateToDetail: (String?) -> Unit,
) {

    val showProgressBarState = remember { mutableStateOf(false) }
    val showProgressBar by rememberUpdatedState(showProgressBarState.value)

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        scaffoldState = scaffoldState,
        topBar = { TopBar(title = stringResource(R.string.random_meal), isButtonShown = false) },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .background(LightGreen),
                contentAlignment = Alignment.Center
            ) {
                GetMealData(viewModel, showProgressBarState, navigateToDetail)
            }
        },
    )
    if (showProgressBar) {
        CustomCircularProgressBar()
    }
}

@Composable
fun GetMealData(
    viewModel: RandomMealViewModel,
    showProgressBarState: MutableState<Boolean>,
    onItemClick: (String?) -> Unit
) {
    val viewState: MealUiState? by viewModel.randomMeal.observeAsState()

    when (viewState) {
        is MealUiState.Error -> {
            showProgressBarState.value = true
        }

        MealUiState.Loading -> {
            showProgressBarState.value = true
        }

        is MealUiState.Success -> {
            showProgressBarState.value = true
            val result = (viewState as MealUiState.Success).data[0]
            GridItemView(
                id = result.id,
                imageUrl = result.imageUrl,
                imageHeight = 200.dp,
                name = result.name,
                fontSize = 26.sp,
                onItemClick = {
                    onItemClick.invoke(it)
                })
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

