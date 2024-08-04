package com.longkd.simplemusiccompose.domain.usecase

import com.longkd.simplemusiccompose.domain.model.Song
import com.longkd.simplemusiccompose.domain.service.MusicController
import javax.inject.Inject

/**
 * @Author: longkd
 * @Since: 22:56 - 4/8/24
 */
class AddMediaItemUseCase @Inject constructor(private val musicController: MusicController) {

    operator fun invoke(songs: List<Song>) {
        musicController.addMediaItems(songs)
    }
}