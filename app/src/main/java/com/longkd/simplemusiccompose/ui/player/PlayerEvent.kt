package com.longkd.simplemusiccompose.ui.player

/**
 * @Author: longkd
 * @Since: 16:42 - 11/8/24
 */

sealed interface PlayerEvent {
    data object PauseSong : PlayerEvent
    data object ResumeSong : PlayerEvent
    data object SkipToNextSong : PlayerEvent
    data object SkipToPreviousSong : PlayerEvent
    data class SeekSongToPosition(val position: Long) : PlayerEvent
}