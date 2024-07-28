package com.longkd.simplemusiccompose.data.mapper

import com.longkd.simplemusiccompose.data.db.model.SongDto
import com.longkd.simplemusiccompose.domain.model.Song

/**
 * @Author: longkd
 * @Since: 11:31 - 28/7/24
 */

fun SongDto.toSong() =
    Song(
        mediaId = mediaId,
        title = title,
        subtitle = subtitle,
        songUrl = songUrl,
        imageUrl = imageUrl
    )