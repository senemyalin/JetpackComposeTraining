package com.senemyalin.jetpackcomposetraining.presentation.screens.randommeal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senemyalin.jetpackcomposetraining.R
import com.senemyalin.jetpackcomposetraining.common.NetworkResponse
import com.senemyalin.jetpackcomposetraining.domain.usecase.GetRandomMealUseCase
import com.senemyalin.jetpackcomposetraining.presentation.MealUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomMealViewModel @Inject constructor(
    private val getRandomMealUseCase: GetRandomMealUseCase
) : ViewModel() {

    private val _randomMeal = MutableLiveData<MealUiState>()
    val randomMeal: LiveData<MealUiState> get() = _randomMeal

    init {
        getRandomMeal()
    }

    private fun getRandomMeal() {
        viewModelScope.launch {
            getRandomMealUseCase.invoke().collect {
                when (it) {
                    is NetworkResponse.Error -> _randomMeal.postValue(
                        MealUiState.Error(R.string.unknown_error)
                    )

                    NetworkResponse.Loading -> _randomMeal.postValue(
                        MealUiState.Loading
                    )

                    is NetworkResponse.Success -> _randomMeal.postValue(
                        MealUiState.Success(it.result!!)
                    )
                }
            }
        }
    }
}