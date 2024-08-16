package com.longkd.simplemusiccompose.ui.setting

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @Author: longkd
 * @Since: 23:42 - 16/8/24
 */
@HiltViewModel
class SettingViewModel @Inject constructor() : ViewModel() {
    var uiState by mutableStateOf(SettingUiState())
        private set
}