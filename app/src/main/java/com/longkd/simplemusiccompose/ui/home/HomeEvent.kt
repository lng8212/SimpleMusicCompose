package com.longkd.simplemusiccompose.ui.home

/**
 * @Author: longkd
 * @Since: 22:52 - 28/7/24
 */
sealed interface HomeEvent {
    data object FetchSong : HomeEvent
}