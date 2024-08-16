package com.longkd.simplemusiccompose.ui.setting

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * @Author: longkd
 * @Since: 19:26 - 20/7/24
 */

@Composable
fun SettingRoute(viewModel: SettingViewModel = hiltViewModel()) {
    SettingScreen()
}

@Composable
fun SettingScreen(modifier: Modifier = Modifier) {
    Text(modifier = Modifier.fillMaxSize(), text = "Coming soon!", textAlign = TextAlign.Center)
}