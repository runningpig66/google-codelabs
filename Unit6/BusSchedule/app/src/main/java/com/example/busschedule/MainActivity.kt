package com.example.busschedule

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.busschedule.ui.BusScheduleApp
import com.example.busschedule.ui.theme.BusScheduleTheme

// Unit 6: Build Bus Schedule app
// https://github.com/google-developer-training/basic-android-kotlin-compose-training-bus-schedule-app/tree/main
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusScheduleTheme {
                BusScheduleApp()
            }
        }
    }
}
