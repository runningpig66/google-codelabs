package com.example.sports.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sports.R
import com.example.sports.model.Sport
import com.example.sports.ui.theme.SportsTheme
import com.example.sports.utils.PhonePreviews

/**
 * @author runningpig66
 * @date 3月21日 周六
 * @time 6:13
 * Main composable that serves as container which displays content according to [uiState] and [windowSize]
 */
@Composable
fun SportsApp() {
    val viewModel: SportsViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SportsAppBar(
                isShowingListPage = uiState.isShowingListPage,
                onBackButtonClick = { viewModel.navigateToListPage() }
            )
        }
    ) { innerPadding ->
        if (uiState.isShowingListPage) {
            SportsList(
                sports = uiState.sportsList,
                onClick = {
                    viewModel.updateCurrentSport(it)
                    viewModel.navigateToDetailPage()
                },
                modifier = Modifier.fillMaxWidth(),
                contentPadding = innerPadding,
            )
        } else {
            SportsDetail(
                selectedSport = uiState.currentSport,
                onBackPressed = { viewModel.navigateToListPage() },
                contentPadding = innerPadding
            )
        }
    }
}

/**
 * Composable that displays the topBar and displays back button if back navigation is possible.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SportsAppBar(
    onBackButtonClick: () -> Unit,
    isShowingListPage: Boolean,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = if (!isShowingListPage) {
                    stringResource(R.string.detail_fragment_label)
                } else {
                    stringResource(R.string.list_fragment_label)
                }
            )
        },
        navigationIcon = {
            if (!isShowingListPage) {
                IconButton(onClick = onBackButtonClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            } else {
                Box {}
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier
    )
}

@Composable
private fun SportsListItem(
    sport: Sport,
    onItemClick: (Sport) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(),
        modifier = modifier,
        shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
        onClick = { onItemClick(sport) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(R.dimen.card_image_height))
        ) {
            SportsListImageItem(
                sport = sport,
                modifier = Modifier.size(dimensionResource(R.dimen.card_image_height))
            )
            Column(
                modifier = Modifier
                    .padding(
                        vertical = dimensionResource(R.dimen.padding_small),
                        horizontal = dimensionResource(R.dimen.padding_medium)
                    )
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(sport.titleResourceId),
                    modifier = Modifier.padding(bottom = dimensionResource(R.dimen.card_text_vertical_space)),
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = stringResource(sport.subtitleResourceId),
                    color = MaterialTheme.colorScheme.secondary,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 3,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(Modifier.weight(1f))
                Row {
                    Text(
                        text = pluralStringResource(
                            R.plurals.player_count_caption,
                            sport.playerCount,
                            sport.playerCount
                        ),
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(Modifier.weight(1f))
                    if (sport.olympic) {
                        Text(
                            text = stringResource(R.string.olympic_caption),
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SportsListImageItem(
    sport: Sport,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(sport.imageResourceId),
        contentDescription = null,
        modifier = modifier,
        alignment = Alignment.Center,
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun SportsList(
    sports: List<Sport>,
    onClick: (Sport) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues()
) {
    val layoutDirection = LocalLayoutDirection.current
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(
            start = dimensionResource(R.dimen.padding_medium) +
                    contentPadding.calculateStartPadding(layoutDirection),
            top = contentPadding.calculateTopPadding() +
                    dimensionResource(R.dimen.padding_medium),
            end = dimensionResource(R.dimen.padding_medium) +
                    contentPadding.calculateEndPadding(layoutDirection),
            bottom = if (contentPadding.calculateBottomPadding() > 0.dp) {
                contentPadding.calculateBottomPadding()
            } else {
                dimensionResource(R.dimen.padding_medium)
            }
        ),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
    ) {
        items(sports, key = { sport -> sport.id }) { sport ->
            SportsListItem(
                sport = sport,
                onItemClick = onClick
            )
        }
    }
}

@Composable
private fun SportsDetail(
    selectedSport: Sport,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues
) {
    BackHandler {
        onBackPressed()
    }
    val layoutDirection = LocalLayoutDirection.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState())
            .padding(top = contentPadding.calculateTopPadding())
    ) {
        Box(Modifier.height(dimensionResource(R.dimen.card_image_height))) {
            Image(
                painter = painterResource(selectedSport.sportsImageBanner),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
            Column(
                Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            listOf(Color.Transparent, MaterialTheme.colorScheme.scrim)
                        )
                    )
            ) {
                Text(
                    text = stringResource(selectedSport.titleResourceId),
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.inverseOnSurface,
                    modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_small))
                )
                Row(modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))) {
                    Text(
                        text = pluralStringResource(
                            R.plurals.player_count_caption,
                            selectedSport.playerCount,
                            selectedSport.playerCount
                        ),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.inverseOnSurface
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = stringResource(R.string.olympic_caption),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.inverseOnSurface
                    )
                }
            }
        }
        Text(
            text = stringResource(selectedSport.sportDetails),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(
                start = dimensionResource(R.dimen.padding_detail_content_horizontal) +
                        contentPadding.calculateStartPadding(layoutDirection),
                top = dimensionResource(R.dimen.padding_detail_content_vertical),
                end = dimensionResource(R.dimen.padding_detail_content_horizontal) +
                        contentPadding.calculateEndPadding(layoutDirection),
                bottom = dimensionResource(R.dimen.padding_detail_content_vertical),
            )
        )
        Spacer(
            Modifier
                .height(
                    if (contentPadding.calculateBottomPadding() > 0.dp) {
                        contentPadding.calculateBottomPadding()
                    } else {
                        dimensionResource(R.dimen.padding_medium)
                    }
                )
        )
    }
}

//@Preview
//@Composable
//fun SportsListItemPreview() {
//    SportsListItem(
//        sport = LocalSportsDataProvider.getSportsData()[0],
//        onItemClick = {},
//    )
//}

//@PhonePreviews
//@Composable
//fun SportsListPreview() {
//    SportList(
//        sports = LocalSportsDataProvider.getSportsData(),
//        onClick = {}
//    )
//}

//@PhonePreviews
//@Composable
//fun SportsDetailPreview() {
//    SportsTheme {
//        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//            SportsDetail(
//                selectedSport = LocalSportsDataProvider.getSportsData()[0],
//                onBackPressed = {},
////                modifier = Modifier.padding(innerPadding)
//                contentPadding = innerPadding
//            )
//        }
//    }
//}

@PhonePreviews
@Composable
fun SportsAppPreview() {
    SportsTheme {
        SportsApp()
    }
}
