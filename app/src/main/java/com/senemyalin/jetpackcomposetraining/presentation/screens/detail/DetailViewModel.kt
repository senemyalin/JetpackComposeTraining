package com.senemyalin.jetpackcomposetraining.presentation.screens.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.senemyalin.jetpackcomposetraining.R
import com.senemyalin.jetpackcomposetraining.common.Extensions.fromJson
import com.senemyalin.jetpackcomposetraining.common.NetworkResponse
import com.senemyalin.jetpackcomposetraining.domain.usecase.GetMealDetailsByIdUseCase
import com.senemyalin.jetpackcomposetraining.presentation.MealUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMealDetailsByIdUseCase: GetMealDetailsByIdUseCase
) : ViewModel() {

    private val _details = MutableLiveData<MealUiState>()
    val details: LiveData<MealUiState> get() = _details

    init {
        savedStateHandle.get<String>("mealId")?.let {
            val mealId = it.fromJson<String>()
            if (mealId != null) {
                getMealDetails(mealId)
            }
        }
    }

    private fun getMealDetails(id: String) {
        viewModelScope.launch {
            getMealDetailsByIdUseCase(id).collectLatest {
                when (it) {
                    is NetworkResponse.Error -> _details.postValue(
                        MealUiState.Error(R.string.unknown_error)
                    )

                    NetworkResponse.Loading -> _details.postValue(
                        MealUiState.Loading
                    )

                    is NetworkResponse.Success -> _details.postValue(
                        MealUiState.Success(it.result!!)
                    )
                }
            }
        }

    }
}