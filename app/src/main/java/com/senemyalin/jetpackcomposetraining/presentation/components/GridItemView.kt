package com.senemyalin.jetpackcomposetraining.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun GridItemView(
    id: String,
    imageUrl: String,
    imageHeight: Dp,
    fontSize: TextUnit,
    name: String,
    onItemClick: (String?) -> Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onItemClick.invoke(id) })
            .padding(12.dp),
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(imageHeight),
                contentScale = ContentScale.FillBounds,
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = "Image of the Meal"
            )
            Text(
                modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp),
                fontSize = fontSize,
                textAlign = TextAlign.Center,
                minLines = 2,
                maxLines = 2,
                text = name,
                style = MaterialTheme.typography.h2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}