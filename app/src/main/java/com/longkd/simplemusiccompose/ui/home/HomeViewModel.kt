package com.longkd.simplemusiccompose.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.longkd.simplemusiccompose.di.Dispatcher
import com.longkd.simplemusiccompose.di.MusicDispatcher
import com.longkd.simplemusiccompose.domain.usecase.AddMediaItemUseCase
import com.longkd.simplemusiccompose.domain.usecase.GetSongsUseCase
import com.longkd.simplemusiccompose.domain.usecase.PauseSongUseCase
import com.longkd.simplemusiccompose.domain.usecase.PlaySongUseCase
import com.longkd.simplemusiccompose.domain.usecase.ResumeSongUseCase
import com.longkd.simplemusiccompose.domain.usecase.SkipToNextSongUseCase
import com.longkd.simplemusiccompose.domain.usecase.SkipToPreviousSongUseCase
import com.longkd.simplemusiccompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * @Author: longkd
 * @Since: 22:21 - 28/7/24
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSongsUseCase: GetSongsUseCase,
    private val addMediaItemsUseCase: AddMediaItemUseCase,
    private val playSongUseCase: PlaySongUseCase,
    private val pauseSongUseCase: PauseSongUseCase,
    private val resumeSongUseCase: ResumeSongUseCase,
    private val skipToNextSongUseCase: SkipToNextSongUseCase,
    private val skipToPreviousSongUseCase: SkipToPreviousSongUseCase,
    @Dispatcher(MusicDispatcher.IO) private val ioDispatcher: CoroutineDispatcher,
    @Dispatcher(MusicDispatcher.Main) private val mainDispatcher: CoroutineDispatcher,
) : ViewModel() {

    var uiState by mutableStateOf(HomeUiState(loading = true))
        private set

    fun onEvent(homeEvent: HomeEvent) {
        when (homeEvent) {
            HomeEvent.FetchSong -> getSongs()
            HomeEvent.PlaySong -> playSong()
            is HomeEvent.OnSongSelected -> {
                uiState = uiState.copy(selectedSong = homeEvent.selectedSong)
            }
        }
    }

    private fun playSong() {
        uiState.apply {
            songs?.indexOf(selectedSong)?.let { song ->
                playSongUseCase(song)
            }
        }
    }

    private fun getSongs() {
        viewModelScope.launch(ioDispatcher) {
            getSongsUseCase().collect {
                when (it) {
                    is Resource.Error -> {
                        uiState = uiState.copy(errorMessage = it.message, loading = false)
                    }

                    is Resource.Success -> {
                        withContext(mainDispatcher) {
                            it.data?.let { songs ->
                                addMediaItemsUseCase(songs)
                            }
                        }
                        uiState = uiState.copy(songs = it.data, loading = false)
                    }

                    else -> {}
                }
            }
        }

    }
}