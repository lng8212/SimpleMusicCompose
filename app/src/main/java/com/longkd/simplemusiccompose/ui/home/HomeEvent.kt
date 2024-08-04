package com.longkd.simplemusiccompose.ui.home

import com.longkd.simplemusiccompose.domain.model.Song

/**
 * @Author: longkd
 * @Since: 22:52 - 28/7/24
 */
sealed interface HomeEvent {
    data class OnSongSelected(val selectedSong: Song) : HomeEvent
    data object FetchSong : HomeEvent
    data object PlaySong : HomeEvent
}