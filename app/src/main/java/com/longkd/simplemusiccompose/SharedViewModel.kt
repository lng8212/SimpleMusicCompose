package com.longkd.simplemusiccompose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.longkd.simplemusiccompose.domain.usecase.DestroyMediaControllerUseCase
import com.longkd.simplemusiccompose.domain.usecase.GetCurrentSongPositionUseCase
import com.longkd.simplemusiccompose.domain.usecase.PauseSongUseCase
import com.longkd.simplemusiccompose.domain.usecase.ResumeSongUseCase
import com.longkd.simplemusiccompose.domain.usecase.SetMediaControllerCallbackUseCase
import com.longkd.simplemusiccompose.domain.usecase.SkipToNextSongUseCase
import com.longkd.simplemusiccompose.domain.usecase.SkipToPreviousSongUseCase
import com.longkd.simplemusiccompose.ui.bottom_bar.NowPlayingBottomBarUiEvent
import com.longkd.simplemusiccompose.ui.bottom_bar.NowPlayingBottomBarUiState
import com.longkd.simplemusiccompose.util.PlayerState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

/**
 * @Author: longkd
 * @Since: 21:45 - 10/8/24
 */
@HiltViewModel
class SharedViewModel @Inject constructor(
    private val setMediaControllerCallbackUseCase: SetMediaControllerCallbackUseCase,
    private val getCurrentSongPositionUseCase: GetCurrentSongPositionUseCase,
    private val destroyMediaControllerUseCase: DestroyMediaControllerUseCase,
    private val pauseSongUseCase: PauseSongUseCase,
    private val resumeSongUseCase: ResumeSongUseCase,
    private val skipToPreviousSongUseCase: SkipToPreviousSongUseCase,
    private val skipToNextSongUseCase: SkipToNextSongUseCase,
) : ViewModel() {
    var nowPlayingBottomBarUiState by mutableStateOf(NowPlayingBottomBarUiState())
        private set

    init {
        setMediaControllerCallback()
    }

    fun onEvent(nowPlayingBottomBarUiEvent: NowPlayingBottomBarUiEvent) {
        when (nowPlayingBottomBarUiEvent) {
            NowPlayingBottomBarUiEvent.OnClickBottomBarUi -> {

            }

            NowPlayingBottomBarUiEvent.OnPlayPauseMusic -> {
                if (nowPlayingBottomBarUiState.playerState == PlayerState.PLAYING) {
                    pauseSongUseCase.invoke()
                } else resumeSongUseCase.invoke()
            }

            NowPlayingBottomBarUiEvent.OnPreviousMusic -> {
                skipToPreviousSongUseCase()
            }

            NowPlayingBottomBarUiEvent.OnNextMusic -> {
                skipToNextSongUseCase()
            }
        }
    }

    private fun setMediaControllerCallback() {
        setMediaControllerCallbackUseCase { playerState, currentMusic, currentPosition, totalDuration, isShuffleEnabled, iRepeatOneEnabled ->
            nowPlayingBottomBarUiState = nowPlayingBottomBarUiState.copy(
                playerState = playerState,
                currentSong = currentMusic,
                currentPosition = currentPosition,
                totalDuration = totalDuration,
                isShuffleEnabled = isShuffleEnabled,
                isRepeatOneEnabled = iRepeatOneEnabled
            )
            if (playerState == PlayerState.PLAYING) {
                viewModelScope.launch {
                    while (true) {
                        delay(3.seconds)
                        nowPlayingBottomBarUiState = nowPlayingBottomBarUiState.copy(
                            currentPosition = getCurrentSongPositionUseCase()
                        )
                    }
                }
            }
        }
    }

    fun destroyMediaController() {
        destroyMediaControllerUseCase()
    }
}