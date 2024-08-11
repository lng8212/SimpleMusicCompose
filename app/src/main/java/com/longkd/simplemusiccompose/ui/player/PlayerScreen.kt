package com.longkd.simplemusiccompose.ui.player

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.longkd.simplemusiccompose.ui.player.component.PlayerBody

/**
 * @Author: longkd
 * @Since: 19:13 - 20/7/24
 */

@Composable
internal fun PlayerRoute(playerUiState: PlayerUiState, onNavigateUp: () -> Unit) {
    val viewModel: PlayerViewModel = hiltViewModel()
    PlayerScreen(playerUiState, onNavigateUp) {
        viewModel.onEvent(it)
    }
}

@Composable
private fun PlayerScreen(
    playerUiState: PlayerUiState,
    onNavigateUp: () -> Unit,
    onEvent: (PlayerEvent) -> Unit,
) {
    if (playerUiState.currentSong != null) {
        PlayerBody(
            playerUiState = playerUiState,
            onNavigateUp = onNavigateUp,
            onEvent = onEvent
        )
    }
}