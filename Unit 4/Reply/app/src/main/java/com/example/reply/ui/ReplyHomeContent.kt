package com.example.reply.ui

import androidx.activity.compose.LocalActivity
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.example.reply.R
import com.example.reply.data.Email
import com.example.reply.data.local.LocalAccountsDataProvider
import com.example.reply.ui.theme.ReplyTheme
import com.example.reply.ui.utils.PhonePreviews
import com.example.reply.ui.utils.ReplyUiStateProvider
import com.example.reply.ui.utils.TabletPreviews

/**
 * @author runningpig66
 * @date 2026-01-28 周三
 * @time 8:49
 */
@Composable
fun ReplyListOnlyContent(
    replyUiState: ReplyUiState,
    onEmailCardPressed: (Email) -> Unit,
    modifier: Modifier = Modifier
) {
    val emails = replyUiState.currentMailboxEmails
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.email_list_item_vertical_spacing))
    ) {
        item {
            ReplyHomeTopBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(R.dimen.topbar_padding_vertical))
            )
        }
        items(items = emails, key = { email -> email.id }) { email ->
            ReplyEmailListItem(
                email = email,
                selected = false,
                onCardClick = { onEmailCardPressed(email) }
            )
        }
    }
}

@Composable
fun ReplyListAndDetailContent(
    replyUiState: ReplyUiState,
    onEmailCardPressed: (Email) -> Unit,
    modifier: Modifier = Modifier
) {
    val emails = replyUiState.currentMailboxEmails
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = dimensionResource(R.dimen.email_list_only_horizontal_padding)),
            contentPadding = WindowInsets.statusBars.asPaddingValues(),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.email_list_item_vertical_spacing))
        ) {
            items(items = emails, key = { email -> email.id }) { email ->
                ReplyEmailListItem(
                    email = email,
                    selected = replyUiState.currentSelectedEmail.id == email.id,
                    onCardClick = { onEmailCardPressed(email) }
                )
            }
        }
        val activity = LocalActivity.current
        ReplyDetailsScreen(
            replyUiState = replyUiState,
            onBackPressed = {},
            modifier = Modifier
                .padding(top = dimensionResource(R.dimen.email_list_item_vertical_spacing))
                .weight(1f)
        )
    }
}

@Composable
fun ReplyEmailListItem(
    email: Email,
    selected: Boolean,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = if (selected)
                MaterialTheme.colorScheme.primaryContainer
            else
                MaterialTheme.colorScheme.secondaryContainer
        ),
        onClick = onCardClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.email_list_item_inner_padding))
        ) {
            ReplyEmailItemHeader(
                email = email,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = stringResource(email.subject),
                modifier = Modifier.padding(
                    top = dimensionResource(R.dimen.email_list_item_header_subject_spacing),
                    bottom = dimensionResource(R.dimen.email_list_item_subject_body_spacing)
                ),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyLarge,
            )
            Text(
                text = stringResource(email.body),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Composable
private fun ReplyEmailItemHeader(
    email: Email,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        ReplyProfileImage(
            drawableResource = email.sender.avatar,
            description = stringResource(email.sender.firstName) + " "
                    + stringResource(email.sender.lastName),
            modifier = Modifier.size(dimensionResource(R.dimen.email_header_profile_size))
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(
                    horizontal = dimensionResource(R.dimen.email_header_content_padding_horizontal),
                    vertical = dimensionResource(R.dimen.email_header_content_padding_vertical)
                ),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(email.sender.firstName),
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                text = stringResource(email.createdAt),
                color = MaterialTheme.colorScheme.outline,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Composable
fun ReplyProfileImage(
    @DrawableRes drawableResource: Int,
    description: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Image(
            modifier = Modifier.clip(CircleShape),
            painter = painterResource(drawableResource),
            contentDescription = description
        )
    }
}

@Composable
fun ReplyLogo(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary
) {
    Image(
        painter = painterResource(R.drawable.logo),
        contentDescription = stringResource(R.string.logo),
        modifier = modifier,
        colorFilter = ColorFilter.tint(color)
    )
}

@Composable
private fun ReplyHomeTopBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ReplyLogo(
            modifier = Modifier
                .size(dimensionResource(R.dimen.topbar_logo_size))
                .padding(start = dimensionResource(R.dimen.topbar_logo_padding_start))
        )
        ReplyProfileImage(
            drawableResource = LocalAccountsDataProvider.defaultAccount.avatar,
            description = stringResource(R.string.profile),
            modifier = Modifier
                .padding(end = dimensionResource(R.dimen.topbar_profile_image_padding_end))
                .size(dimensionResource(R.dimen.topbar_profile_image_size))
        )
    }
}

@PhonePreviews
@Composable
fun ReplyListOnlyContentPreview(
    @PreviewParameter(ReplyUiStateProvider::class) uiState: ReplyUiState
) {
    ReplyTheme {
        Surface {
            ReplyListOnlyContent(
                replyUiState = uiState,
                onEmailCardPressed = {}
            )
        }
    }
}

@TabletPreviews
@Composable
fun ReplyListAndDetailContentPreview(
    @PreviewParameter(ReplyUiStateProvider::class) uiState: ReplyUiState
) {
    ReplyTheme {
        // Surface 确保背景色也能正确变成深色
        Surface {
            ReplyListAndDetailContent(
                replyUiState = uiState,
                onEmailCardPressed = {},
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
