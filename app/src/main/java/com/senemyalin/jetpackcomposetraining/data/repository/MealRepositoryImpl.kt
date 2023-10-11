package com.senemyalin.jetpackcomposetraining.data.repository

import com.senemyalin.jetpackcomposetraining.common.ListMapper
import com.senemyalin.jetpackcomposetraining.common.NetworkResponse
import com.senemyalin.jetpackcomposetraining.data.model.Category
import com.senemyalin.jetpackcomposetraining.data.model.FilteredMeal
import com.senemyalin.jetpackcomposetraining.data.model.Meal
import com.senemyalin.jetpackcomposetraining.data.repository.model.MealEntity
import com.senemyalin.jetpackcomposetraining.data.source.RemoteDataSource
import com.senemyalin.jetpackcomposetraining.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val dataListMapper: ListMapper<Meal, MealEntity>
) : MealRepository {

    override fun getRandomMeal(): Flow<NetworkResponse<List<MealEntity>>> =
        flow {
            emit(NetworkResponse.Loading)
            try {
                when (val response = remoteDataSource.getRandomMeal()) {
                    is NetworkResponse.Error -> emit(response)
                    NetworkResponse.Loading -> emit(NetworkResponse.Loading)
                    is NetworkResponse.Success -> {
                        val mappedResponse = dataListMapper.map(response.result)
                        emit(
                            NetworkResponse.Success(
                                mappedResponse
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                emit(NetworkResponse.Error(e))
            }
        }

    override fun searchMeal(searchKey: String): Flow<NetworkResponse<List<MealEntity>>> =
        flow {
            emit(NetworkResponse.Loading)
            try {
                when (val response = remoteDataSource.searchMeal(searchKey)) {
                    is NetworkResponse.Error -> emit(response)
                    NetworkResponse.Loading -> emit(NetworkResponse.Loading)
                    is NetworkResponse.Success -> {
                        val mappedResponse = dataListMapper.map(response.result)
                        emit(
                            NetworkResponse.Success(
                                mappedResponse
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                emit(NetworkResponse.Error(e))
            }
        }

    override fun getCategories(): Flow<NetworkResponse<List<Category>>> =
        flow {
            emit(NetworkResponse.Loading)
            try {
                when (val response = remoteDataSource.getCategories()) {
                    is NetworkResponse.Error -> emit(response)
                    NetworkResponse.Loading -> emit(NetworkResponse.Loading)
                    is NetworkResponse.Success -> emit(response)
                }
            } catch (e: Exception) {
                emit(NetworkResponse.Error(e))
            }
        }

    override fun filterMealByCategory(category: String): Flow<NetworkResponse<List<FilteredMeal>>> =
        flow {
            emit(NetworkResponse.Loading)
            try {
                when (val response = remoteDataSource.filterMealByCategory(category)) {
                    is NetworkResponse.Error -> emit(response)
                    NetworkResponse.Loading -> emit(NetworkResponse.Loading)
                    is NetworkResponse.Success -> emit(response)
                }
            } catch (e: Exception) {
                emit(NetworkResponse.Error(e))
            }
        }


    override fun getMealDetailsById(id: String): Flow<NetworkResponse<List<MealEntity>>> =
        flow {
            emit(NetworkResponse.Loading)
            try {
                when (val response = remoteDataSource.getMealDetailsById(id)) {
                    is NetworkResponse.Error -> emit(response)
                    NetworkResponse.Loading -> emit(NetworkResponse.Loading)
                    is NetworkResponse.Success -> {
                        val mappedResponse = dataListMapper.map(response.result)
                        emit(
                            NetworkResponse.Success(
                                mappedResponse
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                emit(NetworkResponse.Error(e))
            }
        }

}