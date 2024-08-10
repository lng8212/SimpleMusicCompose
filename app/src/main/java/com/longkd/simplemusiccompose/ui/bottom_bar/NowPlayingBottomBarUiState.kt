package com.longkd.simplemusiccompose.ui.bottom_bar

import androidx.compose.runtime.Stable
import com.longkd.simplemusiccompose.domain.model.Song
import com.longkd.simplemusiccompose.util.PlayerState

/**
 * @Author: longkd
 * @Since: 21:48 - 10/8/24
 */

@Stable
data class NowPlayingBottomBarUiState(
    val playerState: PlayerState? = null,
    val currentSong: Song? = null,
    val currentPosition: Long = 0L,
    val totalDuration: Long = 0L,
    val isShuffleEnabled: Boolean = false,
    val isRepeatOneEnabled: Boolean = false,
)