package com.longkd.simplemusiccompose.ui.bottom_bar

/**
 * @Author: longkd
 * @Since: 22:53 - 10/8/24
 */
sealed interface NowPlayingBottomBarUiEvent {
    data object OnClickBottomBarUi : NowPlayingBottomBarUiEvent
    data object OnNextMusic : NowPlayingBottomBarUiEvent
    data object OnPreviousMusic : NowPlayingBottomBarUiEvent
    data object OnPlayPauseMusic : NowPlayingBottomBarUiEvent
}