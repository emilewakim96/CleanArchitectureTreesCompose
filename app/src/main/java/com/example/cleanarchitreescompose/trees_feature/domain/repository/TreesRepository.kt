package com.example.cleanarchitreescompose.trees_feature.domain.repository

import com.example.cleanarchitreescompose.trees_feature.data.data_source.remote.responses.Trees
import com.example.cleanarchitreescompose.trees_feature.domain.util.Resource

interface TreesRepository {

    suspend fun getTreesList(): Resource<Trees>
}