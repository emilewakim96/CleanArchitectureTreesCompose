package com.example.cleanarchitreescompose.trees_feature.data.data_source.remote

import com.example.cleanarchitreescompose.trees_feature.data.data_source.TreesDataSource
import com.example.cleanarchitreescompose.trees_feature.data.data_source.entity.Tree
import com.example.cleanarchitreescompose.trees_feature.data.data_source.remote.responses.Trees
import com.example.cleanarchitreescompose.trees_feature.domain.util.Resource
import com.example.cleanarchitreescompose.trees_feature.presentation.util.DataMapper
import javax.inject.Inject

class TreesRemoteDataSource @Inject constructor(
    private val api: TreesApi
): TreesDataSource {

    override suspend fun getTreesList(): Resource<List<Tree>> {
        val response = try {
            api.getTreesList()
        } catch(e: Exception) {
            return Resource.Error("An unknown error occured.")
        }
        val trees = response.records?.map { DataMapper.mapRecordToTree(it) }
        return if (trees != null) {
            Resource.Success(trees)
        } else {
            Resource.Error("Response should not be null.")
        }
    }

    override suspend fun insertTree(tree: Tree) {}
}