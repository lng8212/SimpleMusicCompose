package com.longkd.simplemusiccompose.domain.usecase

import com.longkd.simplemusiccompose.domain.model.Song
import com.longkd.simplemusiccompose.domain.service.MusicController
import com.longkd.simplemusiccompose.util.PlayerState
import javax.inject.Inject

/**
 * @Author: longkd
 * @Since: 23:09 - 4/8/24
 */
class SetMediaControllerCallbackUseCase @Inject constructor(private val musicController: MusicController) {
    operator fun invoke(
        callback: (
            playerState: PlayerState,
            currentMusic: Song?,
            currentPosition: Long,
            totalDuration: Long,
            isShuffleEnabled: Boolean,
            iRepeatOneEnabled: Boolean,
        ) -> Unit,
    ) {
        musicController.mediaControllerCallback = callback
    }
}