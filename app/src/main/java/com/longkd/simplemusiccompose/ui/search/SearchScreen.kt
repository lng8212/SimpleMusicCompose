package com.longkd.simplemusiccompose.ui.search

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

/**
 * @Author: longkd
 * @Since: 19:26 - 20/7/24
 */

@Composable
fun SearchScreen(modifier: Modifier = Modifier) {
    Text(modifier = Modifier.fillMaxSize(), text = "Search", textAlign = TextAlign.Center)
}