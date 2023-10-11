package com.senemyalin.jetpackcomposetraining.presentation.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.senemyalin.jetpackcomposetraining.R
import com.senemyalin.jetpackcomposetraining.data.repository.model.MealEntity
import com.senemyalin.jetpackcomposetraining.presentation.MealUiState
import com.senemyalin.jetpackcomposetraining.presentation.components.CustomCircularProgressBar
import com.senemyalin.jetpackcomposetraining.presentation.components.TopBar
import com.senemyalin.jetpackcomposetraining.ui.theme.LightGreen
import com.senemyalin.jetpackcomposetraining.ui.theme.MiddleGreen

@Composable
fun DetailScreen(
    viewModel: DetailViewModel = viewModel(), navigateToBack: () -> Unit
) {
    val showProgressBarState = remember { mutableStateOf(false) }
    val showProgressBar by rememberUpdatedState(showProgressBarState.value)

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        scaffoldState = scaffoldState,
        topBar = { TopBar(title = stringResource(id = R.string.detail), true, navigateToBack) },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .background(LightGreen),
                contentAlignment = Alignment.Center
            ) {
                GetDetails(viewModel, showProgressBarState)
            }
        },
    )
    if (showProgressBar) {
        CustomCircularProgressBar()
    }
}

@Composable
fun GetDetails(viewModel: DetailViewModel, showProgressBarState: MutableState<Boolean>) {
    val viewState: MealUiState? by viewModel.details.observeAsState()

    when (viewState) {
        is MealUiState.Error -> {
            showProgressBarState.value = true
        }

        MealUiState.Loading -> {
            showProgressBarState.value = false
        }

        is MealUiState.Success -> {
            showProgressBarState.value = true
            val result = (viewState as MealUiState.Success).data
            if (result.isEmpty().not()) {
                SetDetails(
                    result[0]
                )
            }
            showProgressBarState.value = false
        }

        null -> {
            //DO NOTHING
        }
    }


}

@Composable
fun SetDetails(mealEntity: MealEntity) {
    Card(
        shape = AbsoluteRoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        backgroundColor = MiddleGreen
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = mealEntity.name,
                textAlign = TextAlign.Center,
                fontSize = 28.sp,
                color = Color.White
            )
            Image(
                modifier = Modifier.height(120.dp),
                painter = rememberAsyncImagePainter(model = mealEntity.imageUrl),
                contentDescription = "Detail Image"
            )
            Text(
                text = "Category: ${mealEntity.category}",
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                color = Color.White
            )
            DescriptionContent(content = mealEntity.description)
            IngredientsContent(content = mealEntity.ingredientsWithMeasure)
        }
    }
}

@Composable
fun DescriptionContent(content: String) {
    Card(
        shape = AbsoluteRoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        backgroundColor = Color.LightGray,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Instructions",
                textAlign = TextAlign.Justify,
                fontSize = 16.sp,
                color = Color.White
            )
            Text(
                text = content, textAlign = TextAlign.Center, fontSize = 12.sp, color = Color.White
            )
        }
    }
}

@Composable
fun IngredientsContent(content: ArrayList<String>) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Ingredients",
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            color = Color.White
        )
        content.forEach {
            Text(
                text = "- $it", textAlign = TextAlign.Center, fontSize = 12.sp, color = Color.White
            )
        }
    }
}
