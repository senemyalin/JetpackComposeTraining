package com.senemyalin.jetpackcomposetraining.presentation.screens.category

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.senemyalin.jetpackcomposetraining.R
import com.senemyalin.jetpackcomposetraining.data.model.Category
import com.senemyalin.jetpackcomposetraining.presentation.CategoryUiState
import com.senemyalin.jetpackcomposetraining.presentation.components.CustomCircularProgressBar
import com.senemyalin.jetpackcomposetraining.presentation.components.TopBar
import com.senemyalin.jetpackcomposetraining.ui.theme.LightGreen

@Composable
fun CategoryScreen(viewModel: CategoryViewModel, onItemClick: (String?) -> Unit) {

    val showProgressBarState = remember { mutableStateOf(false) }
    val showProgressBar by rememberUpdatedState(showProgressBarState.value)

    val scaffoldState = rememberScaffoldState()
    val viewState: CategoryUiState? by viewModel.categories.observeAsState()

    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        scaffoldState = scaffoldState,
        topBar = { TopBar(title = stringResource(id = R.string.category), isButtonShown = false) },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .background(LightGreen),
                contentAlignment = Alignment.Center
            ) {
                GetCategoryResults(viewState, showProgressBarState, onItemClick)
            }
        },
    )
    if (showProgressBar) {
        CustomCircularProgressBar()
    }
}

@Composable
fun GetCategoryResults(
    viewState: CategoryUiState?,
    showProgressBarState: MutableState<Boolean>,
    onItemClick: (String?) -> Unit
) {
    val listState = rememberLazyListState()

    when (viewState) {
        is CategoryUiState.Error -> {
            showProgressBarState.value = true
        }

        CategoryUiState.Loading -> {
            showProgressBarState.value = true
        }

        is CategoryUiState.Success -> {
            showProgressBarState.value = true
            val result = viewState.data
            if (result.isEmpty().not()) {
                LazyColumn(
                    state = listState,
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(12.dp)
                ) {
                    items(result) {
                        CategoryItem(category = it, onItemClick)
                    }
                }
            }
            showProgressBarState.value = false
        }

        null -> {
            //DO NOTHING
        }
    }

}

@Composable
fun CategoryItem(category: Category, onItemClick: (String?) -> Unit) {

    Card(
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onItemClick.invoke(category.strCategory) })
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            //Image
            Image(
                modifier = Modifier.height(100.dp),
                painter = rememberAsyncImagePainter(category.strCategoryThumb),
                contentDescription = "Category Image"
            )
            //Name and Description
            Column(
                modifier = Modifier.fillMaxSize().align(Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = category.strCategory, textAlign = TextAlign.Center)
            }
        }
    }
}