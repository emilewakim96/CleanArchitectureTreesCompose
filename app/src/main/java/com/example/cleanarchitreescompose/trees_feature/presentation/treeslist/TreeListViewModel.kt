package com.example.cleanarchitreescompose.trees_feature.presentation.treeslist

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitreescompose.trees_feature.domain.model.Tree
import com.example.cleanarchitreescompose.trees_feature.domain.repository.TreesRepository
import com.example.cleanarchitreescompose.trees_feature.domain.util.Resource
import com.example.cleanarchitreescompose.trees_feature.presentation.util.DataMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TreeListViewModel @Inject constructor(
    private val repository: TreesRepository,
//    private val dispatcher: DispatcherProvider
) : ViewModel() {

    var treesList = mutableStateListOf<Tree>()
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    init {
        loadTreeList()
    }

    fun loadTreeList() {
        viewModelScope.launch {
            isLoading.value = true
            when(val result = repository.getTreesList()) {
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