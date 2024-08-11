package com.longkd.simplemusiccompose.ui.player

import androidx.lifecycle.ViewModel
import com.longkd.simplemusiccompose.domain.usecase.PauseSongUseCase
import com.longkd.simplemusiccompose.domain.usecase.ResumeSongUseCase
import com.longkd.simplemusiccompose.domain.usecase.SeekSongToPositionUseCase
import com.longkd.simplemusiccompose.domain.usecase.SkipToNextSongUseCase
import com.longkd.simplemusiccompose.domain.usecase.SkipToPreviousSongUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @Author: longkd
 * @Since: 16:47 - 11/8/24
 */
@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val pauseSongUseCase: PauseSongUseCase,
    private val resumeSongUseCase: ResumeSongUseCase,
    private val skipToNextSongUseCase: SkipToNextSongUseCase,
    private val skipToNextPreviousSongUseCase: SkipToPreviousSongUseCase,
    private val seekSongToPositionUseCase: SeekSongToPositionUseCase,
) : ViewModel() {


    fun onEvent(playerEvent: PlayerEvent) {
        when (playerEvent) {
            PlayerEvent.PauseSong -> {
                pauseMusic()
            }

            PlayerEvent.ResumeSong -> {
                resumeMusic()
            }

            PlayerEvent.SkipToNextSong -> {
                skipToNextSong()
            }

            PlayerEvent.SkipToPreviousSong -> {
                skipToPreviousSong()
            }

            is PlayerEvent.SeekSongToPosition -> {
                seekToPosition(playerEvent.position)
            }
        }
    }

    private fun pauseMusic() {
        pauseSongUseCase()
    }

    private fun resumeMusic() {
        resumeSongUseCase()
    }

    private fun skipToNextSong() {
        skipToNextSongUseCase()
    }

    private fun skipToPreviousSong() {
        skipToNextPreviousSongUseCase()
    }

    private fun seekToPosition(position: Long) {
        seekSongToPositionUseCase.invoke(position)
    }
}