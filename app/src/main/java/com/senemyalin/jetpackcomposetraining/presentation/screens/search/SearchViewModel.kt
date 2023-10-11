package com.senemyalin.jetpackcomposetraining.presentation.screens.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senemyalin.jetpackcomposetraining.R
import com.senemyalin.jetpackcomposetraining.common.NetworkResponse
import com.senemyalin.jetpackcomposetraining.domain.usecase.SearchMealUseCase
import com.senemyalin.jetpackcomposetraining.presentation.MealUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMealUseCase: SearchMealUseCase
) : ViewModel() {


    private val _searchMeal = MutableLiveData<MealUiState>()
    val searchMeal: LiveData<MealUiState> get() = _searchMeal

    fun searchMeal(searchKey: String) {
        viewModelScope.launch {
            searchMealUseCase(searchKey).collect {
                when (it) {
                    is NetworkResponse.Error -> _searchMeal.postValue(
                        MealUiState.Error(R.string.unknown_error)
                    )

                    NetworkResponse.Loading -> _searchMeal.postValue(
                        MealUiState.Loading
                    )

                    is NetworkResponse.Success -> _searchMeal.postValue(
                        MealUiState.Success(it.result!!)
                    )
                }
            }
        }
    }

}