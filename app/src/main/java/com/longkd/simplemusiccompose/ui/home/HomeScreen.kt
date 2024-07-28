package com.longkd.simplemusiccompose.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.longkd.simplemusiccompose.ui.home.component.HomeLoadingView
import com.longkd.simplemusiccompose.ui.home.component.SongsView

/**
 * @Author: longkd
 * @Since: 19:12 - 20/7/24
 */

@Composable
internal fun HomeRoute(modifier: Modifier = Modifier, viewModel: HomeViewModel = hiltViewModel()) {
    LaunchedEffect(key1 = Unit) {
        viewModel.onEvent(HomeEvent.FetchSong)
    }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HomeScreen(uiState = uiState)
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier, uiState: HomeUiState) {
    when (uiState) {
        is HomeUiState.Loading -> {
            HomeLoadingView()
        }

        is HomeUiState.Error -> {
            //TODO Show Error
        }

        is HomeUiState.Success -> {
            SongsView(listSong = uiState.listSongs)
        }
    }
}