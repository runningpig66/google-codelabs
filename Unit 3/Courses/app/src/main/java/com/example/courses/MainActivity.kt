package com.example.courses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.courses.data.DataSource
import com.example.courses.model.Topic
import com.example.courses.ui.theme.CoursesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoursesTheme {
                val statusBarsPaddingValues = WindowInsets.statusBars.asPaddingValues()
                val topPaddingValue = statusBarsPaddingValues.calculateTopPadding()
                // TODO: 临时代码 最外层 Box 用于叠加状态栏背景
                Box(modifier = Modifier.fillMaxSize()) {
                    // 状态栏背景色
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(topPaddingValue)
                            .background(MaterialTheme.colorScheme.primary)
                    )
                    Surface(
                        color = MaterialTheme.colorScheme.background,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = topPaddingValue)
                    ) {
                        TopicGrid(modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_small)))
                    }
                }
            }
        }
    }

    @Composable
    fun TopicGrid(modifier: Modifier = Modifier) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(
                top = dimensionResource(R.dimen.padding_small),
                bottom = dimensionResource(R.dimen.padding_large)
            ),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
            modifier = modifier
        ) {
            items(DataSource.topics) { topic ->
                TopicCard(topic)
            }
        }
    }

    @Composable
    fun TopicCard(topic: Topic, modifier: Modifier = Modifier) {
        Card(modifier = modifier.height(dimensionResource(R.dimen.image_size))) {
            Row {
                Image(
                    painter = painterResource(topic.imageRes),
                    contentDescription = null,
                    modifier = modifier
                        .fillMaxHeight()
                        .aspectRatio(1F, true),
                    contentScale = ContentScale.Crop
                )
                Column {
                    Text(
                        text = stringResource(topic.name),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(
                            start = dimensionResource(R.dimen.padding_medium),
                            top = dimensionResource(R.dimen.padding_medium),
                            end = dimensionResource(R.dimen.padding_medium),
                            bottom = dimensionResource(R.dimen.padding_small)
                        )
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(R.drawable.ic_grain),
                            contentDescription = null,
                            modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_medium))
                        )
                        Text(
                            text = topic.availableCourses.toString(),
                            style = MaterialTheme.typography.labelMedium,
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(start = dimensionResource(R.dimen.padding_small))
                        )
                    }
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun TopicPreview() {
        CoursesTheme {

            val topic = Topic(R.string.photography, 321, R.drawable.photography)
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TopicCard(topic = topic)
            }

//            TopicGrid()

        }
    }
}