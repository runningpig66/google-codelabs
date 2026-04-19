package com.example.inventory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.inventory.ui.theme.InventoryTheme

// Unit 5: Read and update data with Room
// https://github.com/google-developer-training/basic-android-kotlin-compose-training-inventory-app/tree/main
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InventoryTheme {
                InventoryApp()
            }
        }
    }
}
