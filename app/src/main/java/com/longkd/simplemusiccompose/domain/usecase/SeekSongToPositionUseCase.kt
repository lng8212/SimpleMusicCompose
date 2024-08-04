package com.longkd.simplemusiccompose.domain.usecase

import com.longkd.simplemusiccompose.domain.service.MusicController
import javax.inject.Inject

/**
 * @Author: longkd
 * @Since: 23:06 - 4/8/24
 */
class SeekSongToPositionUseCase @Inject constructor(private val musicController: MusicController) {
    operator fun invoke(position: Long) {
        musicController.seekTo(position)
    }
}