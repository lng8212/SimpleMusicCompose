package com.longkd.simplemusiccompose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.longkd.simplemusiccompose.ui.home.HOME_ROUTE
import com.longkd.simplemusiccompose.ui.profile.PROFILE_ROUTE
import com.longkd.simplemusiccompose.ui.search.SEARCH_ROUTE

/**
 * @Author: longkd
 * @Since: 19:21 - 20/7/24
 */


data class BottomNavigationItem(
    val label: String = "",
    val icon: ImageVector = Icons.Filled.Home,
    val route: String = "",
) {
    fun bottomNavigationItems(): List<BottomNavigationItem> = listOf(
        BottomNavigationItem(label = "Home", icon = Icons.Filled.Home, HOME_ROUTE),
        BottomNavigationItem(label = "Search", icon = Icons.Filled.Search, SEARCH_ROUTE),
        BottomNavigationItem(label = "Profile", icon = Icons.Filled.AccountCircle, PROFILE_ROUTE)
    )
}