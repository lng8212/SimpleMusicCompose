package com.longkd.simplemusiccompose.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.longkd.simplemusiccompose.di.Dispatcher
import com.longkd.simplemusiccompose.di.MusicDispatcher
import com.longkd.simplemusiccompose.domain.usecase.GetSongsUseCase
import com.longkd.simplemusiccompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @Author: longkd
 * @Since: 22:21 - 28/7/24
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSongsUseCase: GetSongsUseCase,
    @Dispatcher(MusicDispatcher.IO) private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private var _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun onEvent(homeEvent: HomeEvent) {
        when (homeEvent) {
            HomeEvent.FetchSong -> getSongs()
        }
    }

    private fun getSongs() {
        viewModelScope.launch(ioDispatcher) {
            getSongsUseCase().collect {
                when (it) {
                    is Resource.Error -> {
                        _uiState.value = HomeUiState.Error(it.message ?: "")
                    }

                    is Resource.Success -> {
                        _uiState.value = HomeUiState.Success(it.data ?: emptyList())
                    }

                    else -> {
                        _uiState.value = HomeUiState.Loading
                    }
                }
            }
        }

    }
}