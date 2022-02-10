package com.example.cleanarchitreescompose.trees_feature.data.data_source.remote.responses

data class Parameters(
    val dataset: String?,
    val facet: List<String>?,
    val format: String?,
    val rows: Int?,
    val start: Int?,
    val timezone: String?
)