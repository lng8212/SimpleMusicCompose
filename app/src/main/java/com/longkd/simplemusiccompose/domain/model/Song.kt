package com.longkd.simplemusiccompose.domain.model

/**
 * @Author: longkd
 * @Since: 11:22 - 28/7/24
 */

data class Song(
    val mediaId: String,
    val title: String,
    val subtitle: String,
    val songUrl: String,
    val imageUrl: String,
)
