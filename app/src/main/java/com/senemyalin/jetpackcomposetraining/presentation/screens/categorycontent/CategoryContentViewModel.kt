package com.senemyalin.jetpackcomposetraining.presentation.screens.categorycontent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senemyalin.jetpackcomposetraining.R
import com.senemyalin.jetpackcomposetraining.common.Extensions.fromJson
import com.senemyalin.jetpackcomposetraining.common.NetworkResponse
import com.senemyalin.jetpackcomposetraining.domain.usecase.FilterMealByCategoryUseCase
import com.senemyalin.jetpackcomposetraining.presentation.FilteredMealUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryContentViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val filterMealByCategoryUseCase: FilterMealByCategoryUseCase
) : ViewModel() {

    private val _categoryContent = MutableLiveData<FilteredMealUiState>()
    val categoryContent: LiveData<FilteredMealUiState> get() = _categoryContent

    private val _categoryName = MutableLiveData<String?>()
    val categoryName: LiveData<String?> get() = _categoryName

    init {
        savedStateHandle.get<String>("categoryName")?.let {
            val categoryName = it.fromJson<String>()
            _categoryName.value = categoryName
            if (categoryName != null) {
                getCategoryContent(categoryName)
            }
        }
    }

    private fun getCategoryContent(categoryName: String) {
        viewModelScope.launch {
            filterMealByCategoryUseCase(categoryName).collect {
                when (it) {
                    is NetworkResponse.Error -> _categoryContent.postValue(
                        FilteredMealUiState.Error(R.string.unknown_error)
                    )

                    NetworkResponse.Loading -> _categoryContent.postValue(
                        FilteredMealUiState.Loading
                    )

                    is NetworkResponse.Success -> _categoryContent.postValue(
                        FilteredMealUiState.Success(it.result!!)
                    )
                }
            }
        }
    }

}