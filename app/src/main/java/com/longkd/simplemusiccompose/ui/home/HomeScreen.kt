package com.longkd.simplemusiccompose.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.longkd.simplemusiccompose.ui.home.component.HomeLoadingView
import com.longkd.simplemusiccompose.ui.home.component.SongsView

/**
 * @Author: longkd
 * @Since: 19:12 - 20/7/24
 */

@Composable
internal fun HomeRoute(viewModel: HomeViewModel = hiltViewModel()) {
    LaunchedEffect(key1 = Unit) {
        viewModel.onEvent(HomeEvent.FetchSong)
    }
    val uiState = viewModel.uiState

    HomeScreen(uiState = uiState) {
        viewModel.onEvent(it)
    }
}

@Composable
fun HomeScreen(uiState: HomeUiState, onEvent: (HomeEvent) -> Unit) {
    with(uiState) {
        when {
            loading == true -> {
                HomeLoadingView()
            }

            errorMessage == null && songs != null -> {
                SongsView(listSong = songs) {
                    onEvent.invoke(HomeEvent.OnSongSelected(it))
                    onEvent.invoke(HomeEvent.PlaySong)
                }
            }
        }

    }
}