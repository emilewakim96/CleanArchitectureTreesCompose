package com.example.cleanarchitreescompose.trees_feature.presentation.util

import com.example.cleanarchitreescompose.R
import com.example.cleanarchitreescompose.trees_feature.presentation.destinations.CoilImageScreenDestination
import com.example.cleanarchitreescompose.trees_feature.presentation.destinations.Destination
import com.example.cleanarchitreescompose.trees_feature.presentation.destinations.TreesListScreenDestination

sealed class BottomNavItem(var title: String, var icon: Int, var destination: Destination) {
    object Home : BottomNavItem(
        title = "Home",
        icon = R.drawable.ic_home,
        destination = TreesListScreenDestination
    )
    object Other: BottomNavItem(
        title = "Other",
        icon = R.drawable.ic_bookmark,
        destination = CoilImageScreenDestination
    )
}
