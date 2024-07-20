package com.longkd.simplemusiccompose.ui.profile

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

/**
 * @Author: longkd
 * @Since: 19:29 - 20/7/24
 */


const val PROFILE_ROUTE = "profile_route"

fun NavGraphBuilder.profileScreen() {
    composable(route = PROFILE_ROUTE) {
        ProfileScreen()
    }
}