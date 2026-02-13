package com.example.reply

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.reply.ui.PhonePreviews
import com.example.reply.ui.ReplyApp
import com.example.reply.ui.theme.ReplyTheme

// Unit 4: Build an adaptive app with dynamic navigation [git checkout starter]
// https://github.com/google-developer-training/basic-android-kotlin-compose-training-reply-app.git
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ReplyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ReplyApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@PhonePreviews
@Composable
fun ReplyAppPreview() {
    ReplyTheme {
        ReplyApp()
    }
}
