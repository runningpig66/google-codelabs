package com.example.sports

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import com.example.sports.ui.SportsApp
import com.example.sports.ui.theme.SportsTheme

/**
 * Activity for Sports app
 */
// Unit 4: Build Sports app
// https://github.com/google-developer-training/basic-android-kotlin-compose-training-sports/tree/main
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SportsTheme {
                val windowSize = calculateWindowSizeClass(this)
                SportsApp(
                    windowSize = windowSize.widthSizeClass,
                    onBackPressed = { finish() }
                )
            }
        }
    }
}
