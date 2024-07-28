package com.longkd.simplemusiccompose.ui.player

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.longkd.simplemusiccompose.util.FadeTransition
import com.longkd.simplemusiccompose.util.SlideTransition

/**
 * @Author: longkd
 * @Since: 19:16 - 20/7/24
 */


const val PLAYER_ROUTE = "player_route"

fun NavController.navigateToPlayer(navOptions: NavOptions? = null) =
    navigate(route = PLAYER_ROUTE, navOptions = navOptions)

fun NavGraphBuilder.playerScreen() {
    composable(route = PLAYER_ROUTE,
        enterTransition = { SlideTransition.slideUp.enterTransition() },
        exitTransition = { FadeTransition.exitTransition() },
        popEnterTransition = { FadeTransition.enterTransition() },
        popExitTransition = { SlideTransition.slideDown.exitTransition() }) {
        PlayerScreen()
    }
}