package com.example.cleanarchitreescompose.trees_feature.data.data_source.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cleanarchitreescompose.trees_feature.data.data_source.remote.responses.Fields
import com.example.cleanarchitreescompose.trees_feature.data.data_source.remote.responses.Geometry
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Tree(
    val datasetid: String?,
    val fields: Fields?,
    val geometry: Geometry?,
    val record_timestamp: String?,
    @PrimaryKey @NonNull val recordid: String
): Parcelable