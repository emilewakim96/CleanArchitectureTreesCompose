package com.example.cleanarchitreescompose.trees_feature.domain.use_case

import com.example.cleanarchitreescompose.trees_feature.data.data_source.remote.responses.Trees
import com.example.cleanarchitreescompose.trees_feature.domain.repository.TreesRepository
import com.example.cleanarchitreescompose.trees_feature.domain.util.Resource

class GetTreesUseCase(
    private val repository: TreesRepository
) {

    suspend operator fun invoke(): Resource<Trees> {
        return repository.getTreesList()
    }
}