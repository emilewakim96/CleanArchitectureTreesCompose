package com.example.cleanarchitreescompose.trees_feature.data.data_source.local

import com.example.cleanarchitreescompose.trees_feature.data.data_source.TreesDataSource
import com.example.cleanarchitreescompose.trees_feature.data.data_source.entity.Tree
import com.example.cleanarchitreescompose.trees_feature.data.data_source.remote.responses.Trees
import com.example.cleanarchitreescompose.trees_feature.domain.util.Resource
import javax.inject.Inject

class TreesLocalDataSource @Inject constructor(
    treesDao: TreeDao
): TreesDataSource {

    override suspend fun getTreesList(): Resource<Trees> {
        TODO("Not yet implemented")
    }

    override suspend fun insertTree(tree: Tree) {
        TODO("Not yet implemented")
    }
}