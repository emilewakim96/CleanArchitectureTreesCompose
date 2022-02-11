package com.example.cleanarchitreescompose.trees_feature.data.repository

import com.example.cleanarchitreescompose.di.qualifier.LocalData
import com.example.cleanarchitreescompose.di.qualifier.RemoteData
import com.example.cleanarchitreescompose.trees_feature.data.data_source.entity.Tree
import com.example.cleanarchitreescompose.trees_feature.data.data_source.local.TreesLocalDataSource
import com.example.cleanarchitreescompose.trees_feature.data.data_source.remote.TreesRemoteDataSource
import com.example.cleanarchitreescompose.trees_feature.data.data_source.remote.responses.Trees
import com.example.cleanarchitreescompose.trees_feature.domain.repository.TreesRepository
import com.example.cleanarchitreescompose.trees_feature.domain.util.Resource
import com.example.cleanarchitreescompose.trees_feature.presentation.util.DataMapper
import javax.inject.Inject

class TreesRepositoryImpl @Inject constructor(
    @LocalData private val localDataSource: TreesLocalDataSource,
    @RemoteData private val remoteDataSource: TreesRemoteDataSource
): TreesRepository {

    private var cachedTrees: Trees? = null
    private var cachedTreesIsDirty = false

    override suspend fun getTreesList(): Resource<Trees> {
        cachedTrees?.let {
            if (!cachedTreesIsDirty) {
                return Resource.Success(it)
            }
        }
        val remoteTrees = getAndSaveRemoteTrees()
        return if (cachedTreesIsDirty)
            remoteTrees
        else {
            getAndCacheLocalTrees()
        }
    }

    override suspend fun saveTree(tree: Tree) {
        localDataSource.insertTree(tree)
    }

    override fun refreshTrees() {
        cachedTreesIsDirty = true
    }

    private suspend fun getAndSaveRemoteTrees(): Resource<Trees> {
        val response = try {
            remoteDataSource.getTreesList().also {
                it.data?.let { trees ->
                    trees.records?.forEach { record ->
                        saveTree(DataMapper.mapRecordToTree(record))
                    }
                    cachedTreesIsDirty = false
                    cachedTrees = trees
                }
            }
        } catch (e: Exception) {
            return Resource.Error("An unknown error occured.")
        }
        return response
    }

    private suspend fun getAndCacheLocalTrees(): Resource<Trees> {
        return localDataSource.getTreesList().also {
            cachedTrees = it.data
        }
    }
}