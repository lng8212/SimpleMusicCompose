package com.longkd.simplemusiccompose.ui.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

/**
 * @Author: longkd
 * @Since: 19:14 - 20/7/24
 */


const val HOME_ROUTE = "home_route"

fun NavGraphBuilder.homeScreen() {
    composable(route = HOME_ROUTE) {
        HomeRoute()
    }
}