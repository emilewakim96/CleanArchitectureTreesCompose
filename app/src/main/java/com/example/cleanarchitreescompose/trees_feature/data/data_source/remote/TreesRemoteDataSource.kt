package com.example.cleanarchitreescompose.trees_feature.data.data_source.remote

import com.example.cleanarchitreescompose.trees_feature.data.data_source.TreesDataSource
import com.example.cleanarchitreescompose.trees_feature.data.data_source.entity.Tree
import com.example.cleanarchitreescompose.trees_feature.data.data_source.remote.responses.Trees
import com.example.cleanarchitreescompose.trees_feature.domain.util.Resource
import javax.inject.Inject

class TreesRemoteDataSource @Inject constructor(
    private val api: TreesApi
): TreesDataSource {

    override suspend fun getTreesList(): Resource<Trees> {
        val response = try {
            api.getTreesList()
        } catch(e: Exception) {
            return Resource.Error("An unknown error occured.")
        }
        return Resource.Success(response)
    }

    override suspend fun insertTree(tree: Tree) {}
}