package com.longkd.simplemusiccompose.ui.home

import androidx.compose.runtime.Stable
import com.longkd.simplemusiccompose.domain.model.Song

/**
 * @Author: longkd
 * @Since: 22:22 - 28/7/24
 */
@Stable
sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Error(val error: String) : HomeUiState
    data class Success(val listSongs: List<Song>) : HomeUiState
}