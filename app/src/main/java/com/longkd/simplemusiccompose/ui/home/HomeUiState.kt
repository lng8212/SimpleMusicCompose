package com.longkd.simplemusiccompose.ui.home

import androidx.compose.runtime.Stable
import com.longkd.simplemusiccompose.domain.model.Song

/**
 * @Author: longkd
 * @Since: 22:22 - 28/7/24
 */
@Stable
data class HomeUiState(
    val loading: Boolean? = false,
    val songs: List<Song>? = emptyList(),
    val selectedSong: Song? = null,
    val errorMessage: String? = null,
)