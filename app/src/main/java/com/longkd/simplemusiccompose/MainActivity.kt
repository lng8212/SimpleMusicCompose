package com.longkd.simplemusiccompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.longkd.simplemusiccompose.ui.App
import com.longkd.simplemusiccompose.ui.theme.SimpleMusicComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpleMusicComposeTheme {
                App()
            }
        }
    }
}


