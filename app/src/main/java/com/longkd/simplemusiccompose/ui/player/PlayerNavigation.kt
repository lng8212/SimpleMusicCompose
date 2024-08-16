package com.longkd.simplemusiccompose.ui.player

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.longkd.simplemusiccompose.util.FadeTransition
import com.longkd.simplemusiccompose.util.SlideTransition

/**
 * @Author: longkd
 * @Since: 19:16 - 20/7/24
 */


const val PLAYER_ROUTE = "player_route"

fun NavController.navigateToPlayer() = navigate(route = PLAYER_ROUTE)

fun NavGraphBuilder.playerScreen(playerUiState: PlayerUiState, onNavigateUp: () -> Unit) {
    composable(route = PLAYER_ROUTE,
        enterTransition = { SlideTransition.slideUp.enterTransition() },
        exitTransition = { FadeTransition.exitTransition() },
        popEnterTransition = { FadeTransition.enterTransition() },
        popExitTransition = { SlideTransition.slideDown.exitTransition() }) {
        PlayerRoute(playerUiState, onNavigateUp)
    }
}
