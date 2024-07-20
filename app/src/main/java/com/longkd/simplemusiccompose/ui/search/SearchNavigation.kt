package com.longkd.simplemusiccompose.ui.search

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

/**
 * @Author: longkd
 * @Since: 19:27 - 20/7/24
 */

const val SEARCH_ROUTE = "search_route"

fun NavGraphBuilder.searchScreen() {
    composable(route = SEARCH_ROUTE) {
        SearchScreen()
    }
}