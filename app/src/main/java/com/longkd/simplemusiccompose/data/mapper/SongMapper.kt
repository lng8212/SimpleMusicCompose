package com.longkd.simplemusiccompose.data.mapper

import androidx.media3.common.MediaItem
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

fun MediaItem.toSong() =
    Song(
        mediaId = mediaId,
        title = mediaMetadata.title.toString(),
        subtitle = mediaMetadata.subtitle.toString(),
        songUrl = mediaId,
        imageUrl = mediaMetadata.artworkUri.toString()
    )