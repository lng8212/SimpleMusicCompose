package com.longkd.simplemusiccompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import com.longkd.simplemusiccompose.domain.repository.MusicRepository
import com.longkd.simplemusiccompose.ui.App
import com.longkd.simplemusiccompose.ui.theme.SimpleMusicComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var musicRepository: MusicRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        lifecycleScope.launch {
            musicRepository.getSong().collect {
                Log.e("xxxxx", "onCreate: ${it.data?.size}")
            }
        }
        setContent {
            SimpleMusicComposeTheme {
                App()
            }
        }
    }
}


