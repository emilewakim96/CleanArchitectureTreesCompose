package com.example.cleanarchitreescompose.trees_feature.data.repository

import com.example.cleanarchitreescompose.trees_feature.data.data_source.remote.TreesApi
import com.example.cleanarchitreescompose.trees_feature.data.data_source.remote.responses.Trees
import com.example.cleanarchitreescompose.trees_feature.domain.repository.TreesRepository
import com.example.cleanarchitreescompose.trees_feature.domain.util.Resource
import javax.inject.Inject

class TreesRepositoryImpl @Inject constructor(
    private val api: TreesApi
): TreesRepository {

    override suspend fun getTreesList(): Resource<Trees> {
        val response = try {
            api.getTreesList()
        } catch(e: Exception) {
            return Resource.Error("An unknown error occured.")
        }
        return Resource.Success(response)
    }
}