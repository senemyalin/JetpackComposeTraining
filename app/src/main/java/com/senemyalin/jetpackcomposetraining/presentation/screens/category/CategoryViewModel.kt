package com.senemyalin.jetpackcomposetraining.presentation.screens.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senemyalin.jetpackcomposetraining.R
import com.senemyalin.jetpackcomposetraining.common.NetworkResponse
import com.senemyalin.jetpackcomposetraining.data.model.Category
import com.senemyalin.jetpackcomposetraining.domain.usecase.GetCategoriesUseCase
import com.senemyalin.jetpackcomposetraining.presentation.CategoryUiState
import com.senemyalin.jetpackcomposetraining.presentation.FilteredMealUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel(){

    private val _categories = MutableLiveData<CategoryUiState>()
    val categories: LiveData<CategoryUiState> get() = _categories

    init {
        getCategories()
    }

    private fun getCategories(){
        viewModelScope.launch {
            getCategoriesUseCase().collect {
                when(it){
                    is NetworkResponse.Error -> _categories.postValue(
                        CategoryUiState.Error(R.string.unknown_error)
                    )
                    NetworkResponse.Loading -> _categories.postValue(
                        CategoryUiState.Loading
                    )
                    is NetworkResponse.Success -> _categories.postValue(
                        CategoryUiState.Success(it.result!!)
                    )
                }
            }
        }
    }
}