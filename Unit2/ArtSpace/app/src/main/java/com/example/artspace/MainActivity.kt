package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // TODO 适配暗色主题
            ArtSpaceTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    ArtSpaceApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Suppress("COMPOSE_APPLIER_CALL_MISMATCH")
@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {
    var currentIndex by remember { mutableIntStateOf(0) }
    val images = listOf(R.drawable.image_1, R.drawable.image_2, R.drawable.image_3, R.drawable.image_4)
    val titles = listOf(R.string.title_1, R.string.title_2, R.string.title_3, R.string.title_4)
    val artists = listOf(R.string.artist_1_name, R.string.artist_2_name, R.string.artist_3_name, R.string.artist_4_name)
    val years = listOf(R.string.artist_1_year, R.string.artist_2_year, R.string.artist_3_year, R.string.artist_4_year)

    BoxWithConstraints(
        modifier = modifier.fillMaxSize()
    ) {
        // 判断是否是竖屏
        val isPortrait = maxHeight > maxWidth
        // 屏幕的高宽比，用于判断是手机还是平板
        val screenRatio = maxHeight / maxWidth

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isPortrait) { // 竖屏
                Spacer(modifier = Modifier.weight(1f))
                if (screenRatio > 1.7f) { // 手机竖屏
                    ArtImageDisplay(imageRes = images[currentIndex], modifier = Modifier.fillMaxWidth())
                } else { // 平板竖屏
                    ArtImageDisplay(imageRes = images[currentIndex], modifier = Modifier.fillMaxWidth(0.7f))
                }
                Spacer(modifier = Modifier.weight(1f))
            } else { // 横屏
                // TODO 手机横屏状态下，屏幕高度不足，图片布局 weight = 1f 导致过度缩小，学习屏幕适配后完善
                ArtImageDisplay(imageRes = images[currentIndex], modifier = Modifier.weight(1f)) // 横屏状态占满屏幕剩余高度
            }
            ArtDescription(
                titleRes = titles[currentIndex],
                artistRes = artists[currentIndex],
                yearRes = years[currentIndex]
            )
            ArtNavigationControls(
                previousOnClick = {
                    currentIndex = (currentIndex - 1 + images.size) % images.size
                },
                nextOnClick = {
                    currentIndex = (currentIndex + 1) % images.size
                }
            )
        }
    }
}

// 上方的图片布局
@Composable
fun ArtImageDisplay(
    @DrawableRes imageRes: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        shadowElevation = 8.dp,
        modifier = modifier.padding(top = 20.dp) // 这里的padding是为了对称下方文本的topPadding 20.dp
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .aspectRatio(3f / 4f)
                .padding(44.dp),
            contentScale = ContentScale.Crop
        )
    }
}

// 图片下方的描述文本
@Composable
fun ArtDescription(
    @StringRes titleRes: Int,
    @StringRes artistRes: Int,
    @StringRes yearRes: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.padding(top = 20.dp)
    ) {
        Text(
            // TODO 没法给每个 Span 单独设置 maxLines, maxLines 只能作用在整个 Text 上
            text = buildAnnotatedString {
                // title
                withStyle(
                    style = SpanStyle(
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Light
                    )
                ) {
                    append(stringResource(id = titleRes) + "\n")
                }
                // artist
                withStyle(
                    style = SpanStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(stringResource(id = artistRes) + " ")
                }
                // year
                withStyle(
                    style = SpanStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Light,
                        baselineShift = BaselineShift(0.16f)
                    )
                ) {
                    append(stringResource(id = yearRes))
                }
            },
            modifier = Modifier
                .background(Color(0xFFECEBF4))
                .padding(20.dp),
            textAlign = TextAlign.Start,
            lineHeight = 30.sp
        )
    }
}

// 底部的 2 个导航按钮
@Composable
fun ArtNavigationControls(
    previousOnClick: () -> Unit,
    nextOnClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 10.dp, top = 30.dp, end = 10.dp, bottom = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        NavButton(
            textRes = R.string.previous,
            onClick = previousOnClick
        )
        NavButton(
            textRes = R.string.next,
            onClick = nextOnClick
        )
    }
}

@Composable
private fun NavButton(
    @StringRes textRes: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .width(128.dp)
            .height(44.dp)
    ) {
        Text(
            text = stringResource(textRes),
            fontSize = 14.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            ArtSpaceApp(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
