package com.longkd.simplemusiccompose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.longkd.simplemusiccompose.data.service.MusicService
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

    override fun onDestroy() {
        sharedViewModel.destroyMediaController()
        stopService(Intent(this, MusicService::class.java))
        super.onDestroy()
    }
}


