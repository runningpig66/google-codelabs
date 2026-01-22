package com.example.lunchtray

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.lunchtray.ui.theme.LunchTrayTheme

// Unit 4: Lunch Tray App [git checkout starter]
// https://github.com/google-developer-training/basic-android-kotlin-compose-training-lunch-tray.git
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LunchTrayTheme {
                LunchTrayApp()
            }
        }
    }
}
