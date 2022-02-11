package com.example.cleanarchitreescompose.trees_feature.presentation.treeslist

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitreescompose.trees_feature.data.data_source.entity.Tree
import com.example.cleanarchitreescompose.trees_feature.domain.use_case.TreesUseCases
import com.example.cleanarchitreescompose.trees_feature.domain.util.DispatcherProvider
import com.example.cleanarchitreescompose.trees_feature.domain.util.Resource
import com.example.cleanarchitreescompose.trees_feature.presentation.util.DataMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TreeListViewModel @Inject constructor(
    private val treesUseCases: TreesUseCases,
    private val dispatcher: DispatcherProvider
) : ViewModel() {

    var treesList = mutableStateListOf<Tree>()
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    init {
        loadTreeList()
    }

    fun loadTreeList() {
        viewModelScope.launch(dispatcher.main) {
            isLoading.value = true
            when(val result = treesUseCases.getTreesUseCase()) {
                is Resource.Success -> {
                    val trees = result.data?.records?.map {
                        DataMapper.mapRecordToTree(it)
                    }
                    trees?.let {
                        treesList.addAll(it)
                    }
                    loadError.value = ""
                    isLoading.value = false
                }
                else -> {
                    loadError.value = result.message!!
                    isLoading.value = false
                }
            }
        }
    }
}