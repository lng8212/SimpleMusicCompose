package com.longkd.simplemusiccompose.ui.profile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

/**
 * @Author: longkd
 * @Since: 19:28 - 20/7/24
 */

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Text(modifier = Modifier.fillMaxSize(), text = "Profile", textAlign = TextAlign.Center)
}