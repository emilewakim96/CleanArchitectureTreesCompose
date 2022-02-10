package com.example.cleanarchitreescompose.trees_feature.data.data_source.remote.responses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Geometry(
    val coordinates: List<Double>?,
    val type: String?
): Parcelable