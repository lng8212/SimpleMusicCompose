package com.longkd.simplemusiccompose.domain.usecase

import com.longkd.simplemusiccompose.domain.repository.MusicRepository
import javax.inject.Inject

/**
 * @Author: longkd
 * @Since: 22:21 - 28/7/24
 */
class GetSongsUseCase @Inject constructor(private val musicRepository: MusicRepository) {
    operator fun invoke() = musicRepository.getSong()
}