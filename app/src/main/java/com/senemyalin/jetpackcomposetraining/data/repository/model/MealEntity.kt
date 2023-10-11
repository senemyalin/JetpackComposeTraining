package com.senemyalin.jetpackcomposetraining.data.repository.model


data class MealEntity(
    val id: String,
    val name: String,
    val category: String,
    val imageUrl: String,
    val description: String,
    val ingredientsWithMeasure: ArrayList<String>,
)