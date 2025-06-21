package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BusinessCard(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun BusinessCard(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFD4E8D3))
    ) {
        LogoInfo(Modifier.align(Alignment.Center))
        ContactInfo(Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
private fun LogoInfo(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.android_logo),
            contentDescription = null,
            modifier = Modifier
                .height(120.dp)
                .width(120.dp)
                .background(Color(0xFF103043))
        )
        Text(
            text = stringResource(R.string.full_name),
            modifier = Modifier.padding(vertical = 8.dp),
            color = Color(0xFF071A0C),
            fontSize = 40.sp,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
        Text(
            text = stringResource(R.string.title),
            modifier = Modifier.padding(top = 8.dp),
            color = Color(0xFF1D6A33),
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}

@Composable
private fun ContactInfo(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp)
            .wrapContentWidth(Alignment.CenterHorizontally),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        ContactInfoLine(
            description = stringResource(R.string.phone_number),
            painter = painterResource(R.drawable.baseline_phone_24)
        )
        ContactInfoLine(
            description = stringResource(R.string.social_media_handle),
            painter = painterResource(R.drawable.baseline_share_24),
            modifier = Modifier.padding(top = 12.dp)
        )
        ContactInfoLine(
            description = stringResource(R.string.email_address),
            painter = painterResource(R.drawable.baseline_email_24),
            modifier = Modifier.padding(vertical = 12.dp)
        )
    }
}

@Composable
private fun ContactInfoLine(description: String, painter: Painter, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = Color(0xFF1E6D39)
        )
        Text(
            text = description,
            modifier = Modifier.padding(start = 24.dp),
            color = Color(0xFF0D1E10),
            fontSize = 18.sp,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BusinessCardPreview() {
    BusinessCardTheme {
        BusinessCard()
    }
}