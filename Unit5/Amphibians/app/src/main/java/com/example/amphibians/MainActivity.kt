package com.example.amphibians

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.amphibians.ui.AmphibiansApp
import com.example.amphibians.ui.theme.AmphibiansTheme

// Unit 5: Get data from the internet
// https://github.com/google-developer-training/basic-android-kotlin-compose-training-amphibians/tree/main
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AmphibiansTheme {
                AmphibiansApp()
            }
        }
    }
}
