package com.example.marsphotos.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.marsphotos.R
import com.example.marsphotos.ui.theme.MarsPhotosTheme
import com.example.marsphotos.ui.utils.PhonePreviews

/**
 * @author runningpig66
 * @date 2026-04-02
 * @time 23:34
 */
@Composable
fun HomeScreen(
    marsUiState: String,
    modifier: Modifier = Modifier
) {
    ResultScreen(
        photos = marsUiState,
        modifier = modifier
    )
}

/**
 * ResultScreen displaying number of photos retrieved.
 */
@Composable
fun ResultScreen(photos: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(text = photos)
    }
}

@PhonePreviews
@Composable
fun ResultScreenPreview() {
    MarsPhotosTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            ResultScreen(
                photos = stringResource(R.string.placeholder_result),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )
        }
    }
}
