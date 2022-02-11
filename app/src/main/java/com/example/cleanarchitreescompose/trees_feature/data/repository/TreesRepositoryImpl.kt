package com.example.cleanarchitreescompose.trees_feature.data.repository

import com.example.cleanarchitreescompose.di.qualifier.LocalData
import com.example.cleanarchitreescompose.di.qualifier.RemoteData
import com.example.cleanarchitreescompose.trees_feature.data.data_source.local.TreesLocalDataSource
import com.example.cleanarchitreescompose.trees_feature.data.data_source.remote.TreesRemoteDataSource
import com.example.cleanarchitreescompose.trees_feature.data.data_source.remote.responses.Trees
import com.example.cleanarchitreescompose.trees_feature.domain.repository.TreesRepository
import com.example.cleanarchitreescompose.trees_feature.domain.util.Resource
import javax.inject.Inject

class TreesRepositoryImpl @Inject constructor(
    @LocalData private val localDataSource: TreesLocalDataSource,
    @RemoteData private val remoteDataSource: TreesRemoteDataSource
): TreesRepository {

    override suspend fun getTreesList(): Resource<Trees> {
        val response = try {
            remoteDataSource.getTreesList()
        } catch(e: Exception) {
            return Resource.Error("An unknown error occured.")
        }
        return response
    }
}