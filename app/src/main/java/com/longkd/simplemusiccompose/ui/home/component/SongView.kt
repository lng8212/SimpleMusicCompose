package com.longkd.simplemusiccompose.ui.home.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.longkd.simplemusiccompose.domain.model.Song

/**
 * @Author: longkd
 * @Since: 23:26 - 28/7/24
 */
@Composable
fun SongsView(listSong: List<Song>) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            items(listSong, key = {
                it.mediaId
            }) { song ->
                SongItem(item = song)
            }
        }
    }
}