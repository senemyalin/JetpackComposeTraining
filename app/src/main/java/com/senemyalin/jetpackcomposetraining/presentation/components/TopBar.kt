package com.senemyalin.jetpackcomposetraining.presentation.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.senemyalin.jetpackcomposetraining.ui.theme.DarkGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title: String, isButtonShown: Boolean, navigateToBack: (() -> Unit)? = null) {

    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = DarkGreen
        ),
        title = {
            Text(text = title, color = Color.White, fontSize = 24.sp)
        },
        navigationIcon = {
            if (isButtonShown && navigateToBack != null) {
                IconButton(onClick = {
                    navigateToBack()
                }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back button", tint = Color.White)
                }
            }
        }
    )
}
