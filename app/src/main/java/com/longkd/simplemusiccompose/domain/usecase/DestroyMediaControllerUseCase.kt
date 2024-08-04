package com.longkd.simplemusiccompose.domain.usecase

import com.longkd.simplemusiccompose.domain.service.MusicController
import javax.inject.Inject

/**
 * @Author: longkd
 * @Since: 22:57 - 4/8/24
 */
class DestroyMediaControllerUseCase @Inject constructor(private val musicController: MusicController) {
    operator fun invoke() {
        musicController.destroy()
    }
}