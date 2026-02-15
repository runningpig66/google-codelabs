package com.example.reply

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.reply.ui.ReplyApp
import com.example.reply.ui.theme.ReplyTheme
import com.example.reply.ui.utils.PhonePreviews
import com.example.reply.ui.utils.TabletPreviews

// Unit 4: Build an adaptive app with dynamic navigation [git checkout nav-update]
// https://github.com/google-developer-training/basic-android-kotlin-compose-training-reply-app.git
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ReplyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val windowSize = calculateWindowSizeClass(this)
                    ReplyApp(
                        windowSize = windowSize.widthSizeClass,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@TabletPreviews
@Composable
fun ReplyAppPreviewT() {
    ReplyTheme {
        ReplyApp(windowSize = WindowWidthSizeClass.Expanded)
    }
}

@PhonePreviews
@Composable
fun ReplyAppPreviewP() {
    ReplyTheme {
        ReplyApp(windowSize = WindowWidthSizeClass.Compact)
    }
}
