package com.example.reply.ui

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.reply.data.Email
import com.example.reply.data.MailboxType
import com.example.reply.ui.utils.ReplyNavigationType

/**
 * @author runningpig66
 * @date 2月14日 周六
 * @time 3:45
 */
@Composable
fun ReplyApp(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier
) {
    val viewModel: ReplyViewModel = viewModel()
    val replyUiState = viewModel.uiState.collectAsState().value
    val navigationType = when (windowSize) {
        WindowWidthSizeClass.Compact -> ReplyNavigationType.BOTTOM_NAVIGATION
        WindowWidthSizeClass.Medium -> ReplyNavigationType.NAVIGATION_RAIL
        WindowWidthSizeClass.Expanded -> ReplyNavigationType.PERMANENT_NAVIGATION_DRAWER
        else -> ReplyNavigationType.BOTTOM_NAVIGATION
    }
    ReplyHomeScreen(
        navigationType = navigationType,
        replyUiState = replyUiState,
        onTabPressed = { mailboxType: MailboxType ->
            viewModel.updateCurrentMailbox(mailboxType)
            viewModel.resetHomeScreenStates()
        },
        onEmailCardPressed = { email: Email -> viewModel.updateDetailsScreenStates(email) },
        onDetailScreenBackPressed = { viewModel.resetHomeScreenStates() },
        modifier = modifier
    )
}
