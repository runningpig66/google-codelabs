package com.example.reply

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.reply.ui.ReplyApp
import com.example.reply.ui.theme.ReplyTheme

// Unit 4: Build an adaptive app with dynamic navigation [git checkout main]
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
                        modifier = Modifier
                            .padding(innerPadding)
                            // 消费并更新向下游传递的 WindowInsets 上下文。声明 innerPadding 代表的系统级安全区（如状态栏、全面屏手势区、异形屏挖孔）已被当前 Modifier.padding 处理。
                            // 阻断底层带有默认 Insets 探测机制的组件（如 NavigationRail, PermanentDrawerSheet）对其进行重复计算，从而避免产生双重内边距（Double Padding）导致的布局偏移。
                            .consumeWindowInsets(innerPadding)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ReplyAppCompactPreview() {
    ReplyTheme {
        Surface {
            ReplyApp(
                windowSize = WindowWidthSizeClass.Compact,
            )
        }
    }
}

@Preview(widthDp = 700, showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ReplyAppMediumPreview() {
    ReplyTheme {
        Surface {
            ReplyApp(
                windowSize = WindowWidthSizeClass.Medium,
            )
        }
    }
}

@Preview(widthDp = 1000, showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ReplyAppExpandedPreview() {
    ReplyTheme {
        Surface {
            ReplyApp(
                windowSize = WindowWidthSizeClass.Expanded,
            )
        }
    }
}
