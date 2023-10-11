package com.senemyalin.jetpackcomposetraining.data.repository.mapper

import com.senemyalin.jetpackcomposetraining.common.ListMapper
import com.senemyalin.jetpackcomposetraining.data.model.Meal
import com.senemyalin.jetpackcomposetraining.data.repository.model.MealEntity
import javax.inject.Inject

class DataListMapperImpl @Inject constructor() : ListMapper<Meal, MealEntity> {

    override fun map(input: List<Meal>?): List<MealEntity> {
        return input?.map {
            MealEntity(
                id = it.idMeal,
                name = it.strMeal,
                category = it.strCategory,
                imageUrl = it.strMealThumb,
                description = it.strInstructions,
                ingredientsWithMeasure = getIngredientsFromMeal(it),
            )
        } ?: emptyList()
    }
}

private fun getIngredientsFromMeal(meal: Meal): ArrayList<String> {
    val ingredientsList = ArrayList<String>()

    for (i in 1..20) {
        val ingredient = meal.getIngredient(i)
        val measure = meal.getMeasure(i)

        if (!ingredient.isNullOrBlank() && !measure.isNullOrBlank() && ingredient != "" && measure != "") {
            val ingredientWithMeasure = "$measure $ingredient"
            ingredientsList.add(ingredientWithMeasure)
        }
    }

    return ingredientsList
}


private fun Meal.getIngredient(index: Int): String? {
    return when (index) {
        1 -> strIngredient1
        2 -> strIngredient2
        3 -> strIngredient3
        4 -> strIngredient4
        5 -> strIngredient5
        6 -> strIngredient6
        7 -> strIngredient7
        8 -> strIngredient8
        9 -> strIngredient9
        10 -> strIngredient10
        11 -> strIngredient11
        12 -> strIngredient12
        13 -> strIngredient13
        14 -> strIngredient14
        15 -> strIngredient15
        16 -> strIngredient16
        17 -> strIngredient17
        18 -> strIngredient18
        19 -> strIngredient19
        20 -> strIngredient20
        else -> null
    }
}

private fun Meal.getMeasure(index: Int): String? {
    return when (index) {
        1 -> strMeasure1
        2 -> strMeasure2
        3 -> strMeasure3
        4 -> strMeasure4
        5 -> strMeasure5
        6 -> strMeasure6
        7 -> strMeasure7
        8 -> strMeasure8
        9 -> strMeasure9
        10 -> strMeasure10
        11 -> strMeasure11
        12 -> strMeasure12
        13 -> strMeasure13
        14 -> strMeasure14
        15 -> strMeasure15
        16 -> strMeasure16
        17 -> strMeasure17
        18 -> strMeasure18
        19 -> strMeasure19
        20 -> strMeasure20
        else -> null
    }
}
