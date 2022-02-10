package com.example.cleanarchitreescompose.trees_feature.domain.model

import android.os.Parcelable
import com.example.cleanarchitreescompose.trees_feature.data.data_source.remote.responses.Fields
import com.example.cleanarchitreescompose.trees_feature.data.data_source.remote.responses.Geometry
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tree(
    val datasetid: String?,
    val fields: Fields?,
    val geometry: Geometry?,
    val record_timestamp: String?,
    val recordid: String?
): Parcelable