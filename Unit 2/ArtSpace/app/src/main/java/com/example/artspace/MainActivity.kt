package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
            ArtSpaceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtSpaceApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Spacer(modifier = Modifier.weight(1F))
        /*
         * NOTE: 在设置了 .verticalScroll() 的 Column 中可以使用 .weight()，但要谨慎。
         * 如果屏幕高度仍有大量空余空间，weight 子项可以正常分配并显示；
         * 但在高度空间不足（如横屏或内容过多）时，weight 子项可能被压缩，导致内容显示不完整甚至完全看不到。
         */
        ArtImageDisplay(
            // 所以不应该在这里设置 weight，更不应该将设置后的 modifier 传入子项 Box 中，否则会导致横屏图像压缩，且 Column 不可滚动。
//            modifier = Modifier.weight(1F)
        )
        Spacer(modifier = Modifier.weight(1F))
        ArtDescription()
        ArtNavigationControls()
    }
}

@Composable
fun ArtImageDisplay() {
    Surface(shadowElevation = 8.dp) {
        Image(
            painter = painterResource(R.drawable.image_1),
            contentDescription = null,
            modifier = Modifier
                .width(540.dp)
                .aspectRatio(3F / 4F)
                .padding(44.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun ArtDescription() {
    Text(
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Light,
                )
            ) {
                append(stringResource(R.string.title_1) + "\n")
            }
            withStyle(
                style = SpanStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append(stringResource(R.string.artist_1_name) + " ")
            }
            withStyle(
                style = SpanStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light,
                    baselineShift = BaselineShift(0.16F)
                )
            ) {
                append(stringResource(R.string.artist_1_year))
            }
        },
        modifier = Modifier
            .background(Color(0xFFECEBF4))
            .padding(20.dp),
        textAlign = TextAlign.Start,
        lineHeight = 30.sp
    )
}

@Composable
fun ArtNavigationControls() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = {},
            modifier = Modifier
                .width(160.dp)
                .height(44.dp)
        ) {
            Text(
                text = stringResource(R.string.previous),
                fontSize = 14.sp
            )
        }
        Button(
            onClick = {},
            modifier = Modifier
                .width(160.dp)
                .height(44.dp)
        ) {
            Text(text = stringResource(R.string.next))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}