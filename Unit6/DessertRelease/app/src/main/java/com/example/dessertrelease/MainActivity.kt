package com.example.dessertrelease

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.dessertrelease.ui.DessertReleaseApp
import com.example.dessertrelease.ui.theme.DessertReleaseTheme

// Unit 6: Save preferences locally with DataStore
// https://github.com/google-developer-training/basic-android-kotlin-compose-training-dessert-release/tree/main
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DessertReleaseTheme {
                DessertReleaseApp()
            }
        }
    }
}
