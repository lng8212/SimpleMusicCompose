package com.longkd.simplemusiccompose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.longkd.simplemusiccompose.ui.home.HOME_ROUTE
import com.longkd.simplemusiccompose.ui.setting.SETTING_ROUTE

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
        BottomNavigationItem(label = "Setting", icon = Icons.Filled.Settings, SETTING_ROUTE)
    )
}