package com.senemyalin.jetpackcomposetraining.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.senemyalin.jetpackcomposetraining.ui.theme.DarkGreen

@Composable
fun CustomCircularProgressBar(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
                modifier = Modifier.size(100.dp),
        color = DarkGreen,
        strokeWidth = 10.dp)
    }
}