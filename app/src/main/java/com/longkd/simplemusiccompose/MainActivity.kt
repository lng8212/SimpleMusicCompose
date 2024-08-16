package com.longkd.simplemusiccompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.longkd.simplemusiccompose.ui.App
import com.longkd.simplemusiccompose.ui.theme.SimpleMusicComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val sharedViewModel: SharedViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpleMusicComposeTheme {
                App(
                    sharedViewModel = sharedViewModel
                )
            }
        }
    }

}


