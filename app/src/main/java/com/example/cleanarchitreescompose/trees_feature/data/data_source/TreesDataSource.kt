package com.example.cleanarchitreescompose.trees_feature.data.data_source

import com.example.cleanarchitreescompose.trees_feature.data.data_source.entity.Tree
import com.example.cleanarchitreescompose.trees_feature.data.data_source.remote.responses.Trees
import com.example.cleanarchitreescompose.trees_feature.domain.util.Resource

interface TreesDataSource {

    suspend fun getTreesList(): Resource<Trees>

    suspend fun insertTree(tree: Tree)
}