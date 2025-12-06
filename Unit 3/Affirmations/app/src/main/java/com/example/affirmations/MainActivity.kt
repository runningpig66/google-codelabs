package com.example.affirmations

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.affirmations.data.Datasource
import com.example.affirmations.model.Affirmation
import com.example.affirmations.ui.theme.AffirmationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AffirmationsTheme {
                AffirmationsApp()
            }
        }
    }
}

@Composable
fun AffirmationsApp() {
    // 获取当前布局方向
    val layoutDirection: LayoutDirection = LocalLayoutDirection.current
    val safeDrawingPaddingValues: PaddingValues = WindowInsets.safeDrawing.asPaddingValues()
    val start0: Dp = safeDrawingPaddingValues.calculateStartPadding(layoutDirection)
    val top0 = safeDrawingPaddingValues.calculateTopPadding()
    val end0 = safeDrawingPaddingValues.calculateEndPadding(layoutDirection)
    val bottom0 = safeDrawingPaddingValues.calculateBottomPadding()
    // PaddingTest0 start: 0.0.dp, top: 48.761906.dp, end: 0.0.dp, bottom: 24.0.dp
    Log.d("PaddingTest0", "start: $start0, top: $top0, end: $end0, bottom: $bottom0")

    val statusBarsPaddingValues: PaddingValues = WindowInsets.statusBars.asPaddingValues()
    val start1: Dp = statusBarsPaddingValues.calculateStartPadding(layoutDirection)
    val top1 = statusBarsPaddingValues.calculateTopPadding()
    val end1 = statusBarsPaddingValues.calculateEndPadding(layoutDirection)
    val bottom1 = statusBarsPaddingValues.calculateBottomPadding()
    // PaddingTest1 start: 0.0.dp, top: 48.761906.dp, end: 0.0.dp, bottom: 24.0.dp
    Log.d("PaddingTest1", "start: $start0, top: $top0, end: $end0, bottom: $bottom0")

    Surface(
        modifier = Modifier
            .fillMaxSize()
//            .padding(paddingValues = WindowInsets.statusBars.asPaddingValues())
//            .statusBarsPadding()
//            .padding(paddingValues = WindowInsets.safeDrawing.asPaddingValues())
//            .safeDrawingPadding()
            .padding(top = top0)
    ) {
        AffirmationList(
            affirmationList = Datasource().loadAffirmations()
        )
    }
}

@Composable
fun AffirmationList(affirmationList: List<Affirmation>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(affirmationList) { affirmation ->
            AffirmationCard(
                affirmation = affirmation,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun AffirmationCard(affirmation: Affirmation, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column {
            Image(
                painter = painterResource(affirmation.imageResourceId),
                contentDescription = stringResource(affirmation.stringResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                // 用的是 Android 平台的 Context.getString(...) 方法
                // 如果你修改了语言设置（Locale）或字符串资源，它不会自动刷新界面
                text = LocalContext.current.getString(affirmation.stringResourceId),
//                text = stringResource(affirmation.stringResourceId),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AffirmationPreview() {
    AffirmationsTheme {
        AffirmationsApp()
    }
}
