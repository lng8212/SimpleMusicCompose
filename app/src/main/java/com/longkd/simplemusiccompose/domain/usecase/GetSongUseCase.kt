package com.longkd.simplemusiccompose.domain.usecase

import com.longkd.simplemusiccompose.domain.repository.MusicRepository
import javax.inject.Inject

/**
 * @Author: longkd
 * @Since: 22:59 - 4/8/24
 */
class GetSongUseCase @Inject constructor(private val musicRepository: MusicRepository) {
    operator fun invoke() = musicRepository.getSong()
}