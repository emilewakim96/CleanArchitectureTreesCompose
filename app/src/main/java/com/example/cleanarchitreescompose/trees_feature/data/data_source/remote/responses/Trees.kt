package com.example.cleanarchitreescompose.trees_feature.data.data_source.remote.responses

data class Trees(
    val facet_groups: List<FacetGroup>?,
    val nhits: Int?,
    val parameters: Parameters?,
    val records: List<Record>?
)