package com.example.affirmations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.affirmations.data.Datasource
import com.example.affirmations.model.Affirmation
import com.example.affirmations.ui.theme.AffirmationsTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

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
//    val layoutDirection: LayoutDirection = LocalLayoutDirection.current
//    val safeDrawingPaddingValues: PaddingValues = WindowInsets.safeDrawing.asPaddingValues()
//    val start0: Dp = safeDrawingPaddingValues.calculateStartPadding(layoutDirection)
//    val top0 = safeDrawingPaddingValues.calculateTopPadding()
//    val end0 = safeDrawingPaddingValues.calculateEndPadding(layoutDirection)
//    val bottom0 = safeDrawingPaddingValues.calculateBottomPadding()
//    // PaddingTest0 start: 0.0.dp, top: 48.761906.dp, end: 0.0.dp, bottom: 24.0.dp
//    Log.d("PaddingTest0", "start: $start0, top: $top0, end: $end0, bottom: $bottom0")

//    val statusBarsPaddingValues: PaddingValues = WindowInsets.statusBars.asPaddingValues()
//    val start1: Dp = statusBarsPaddingValues.calculateStartPadding(layoutDirection)
//    val top1 = statusBarsPaddingValues.calculateTopPadding()
//    val end1 = statusBarsPaddingValues.calculateEndPadding(layoutDirection)
//    val bottom1 = statusBarsPaddingValues.calculateBottomPadding()
//    //PaddingTest1 start: 0.0.dp, top: 48.761906.dp, end: 0.0.dp, bottom: 0.0.dp
//    Log.d("PaddingTest1", "start: $start1, top: $top1, end: $end1, bottom: $bottom1")

    val lazyListState: LazyListState = rememberLazyListState()
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    val isShowScrollToTopButton by remember {
        derivedStateOf {
            lazyListState.firstVisibleItemIndex > 0 ||
                    lazyListState.firstVisibleItemScrollOffset > 0
        }
    }
    // 建议：Datasource().loadAffirmations() 这类纯数据最好用 remember 包一层，避免每次重组都重新 new:
    val affirmations = remember { Datasource().loadAffirmations() }

    Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                // 推荐使用内置修饰符替换复杂的计算
//                .safeDrawingPadding()
                .statusBarsPadding()
//                .padding(top = top0)
//                .padding(paddingValues = WindowInsets.statusBars.asPaddingValues())
//                .statusBarsPadding()
//                .padding(paddingValues = WindowInsets.safeDrawing.asPaddingValues())
//                .safeDrawingPadding()
        ) {
            AffirmationList(
                state = lazyListState,
                affirmationList = affirmations
            )

            // 调试视图（仅在设计模式显示），查看 FAB 实际占据的区域
            if (LocalInspectionMode.current) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(16.dp)
                        .size(56.dp)
                        .background(Color.Red.copy(alpha = 0.3f))
                        .border(1.dp, Color.Red)
                )
            }

            if (isShowScrollToTopButton) {
                ScrollToTopButton(
                    onClick = {
                        coroutineScope.launch {
                            lazyListState.animateScrollToItem(0)
                        }
                    },
                    modifier = Modifier.align(Alignment.BottomEnd)
                )
            }
        }
    }
}

@Composable
fun AffirmationList(
    state: LazyListState,
    affirmationList: List<Affirmation>,
    modifier: Modifier = Modifier
) {
    val lazyColumnHorizontalPadding = 16.dp
    // FAB 尺寸
    val fabSize = 56.dp
    val fabMargin = 16.dp
    // Footer 希望紧贴着 FAB leftPadding 的左侧。由于 Footer 在 LazyColumn 的内部，
    // 且 LazyColumn 已经设置了 contentPadding = PaddingValues(horizontal = lazyColumnHorizontalPadding),
    // Footer 在 rightPadding 的时候需要减去右侧已经 padding 过的 lazyColumnHorizontalPadding.
    val footerEndPadding = fabSize + fabMargin * 2 - lazyColumnHorizontalPadding
    // FAB 的 padding 顶部希望与最后一个 Card 的底边齐平，Footer 为了配合这一效果只能减去 FAB 的 顶部 padding 距离。
    val footerHeight = fabSize + fabMargin

    LazyColumn(
        modifier = modifier,
        state = state,
//        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = lazyColumnHorizontalPadding)
    ) {
        item {
            Header()
        }
        items(
            items = affirmationList,
            // 暂时先不使用 key（等学习了状态保持再添加）
//            key = { affirmationList ->
//                // 使用 stringResourceId 和 imageResourceId 的组合作为 key
//                // 假设这个组合是唯一的
//                "${affirmationList.stringResourceId}_${affirmationList.imageResourceId}"
//            }
        ) { item ->
            AffirmationCard(
                affirmation = item,
                modifier = Modifier.padding(bottom = 16.5.dp)
            )
        }
        item {
            Footer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(footerHeight)
                    .padding(end = footerEndPadding)
                    .wrapContentSize(Alignment.CenterEnd)
            )
        }
    }
}

@Composable
fun ScrollToTopButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier.padding(16.dp),
        shape = CircleShape,
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
    ) {
        Icon(
            imageVector = Icons.Filled.KeyboardArrowUp,
            contentDescription = "Scroll to top"
        )
    }
}

@Composable
fun Footer(modifier: Modifier = Modifier) {
    Text(
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    baselineShift = BaselineShift(0.66f)
                )
            ) {
                append("Jetpack Compose")
            }
        },
        modifier = modifier,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleLarge
    )
}

@Composable
fun Header() {
    Text(
        text = "Daily Affirmations",
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        style = MaterialTheme.typography.headlineLarge
    )
}

@Composable
fun AffirmationCard(affirmation: Affirmation, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        // 使用 CardDefaults 提供更好的默认值
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp,
            pressedElevation = 8.dp
        )
    ) {
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
                text = stringResource(affirmation.stringResourceId),
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
