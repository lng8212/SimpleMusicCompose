package com.longkd.simplemusiccompose.domain.usecase

import com.longkd.simplemusiccompose.domain.service.MusicController
import javax.inject.Inject

/**
 * @Author: longkd
 * @Since: 23:01 - 4/8/24
 */
class PlaySongUseCase @Inject constructor(private val musicController: MusicController) {
    operator fun invoke(mediaItemIndex: Int) {
        musicController.play(mediaItemIndex)
    }
}