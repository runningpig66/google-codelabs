package com.example.reply.ui.utils

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.reply.data.MailboxType
import com.example.reply.data.local.LocalEmailsDataProvider
import com.example.reply.ui.ReplyUiState

/**
 * @author runningpig66
 * @date 2月13日 周五
 * @time 0:50
 */
// TODO 定义一个 Provider
class ReplyUiStateProvider : PreviewParameterProvider<ReplyUiState> {
    override val values = sequenceOf(
        ReplyUiState(
            mailboxes = LocalEmailsDataProvider.allEmails.groupBy { it.mailbox },
            currentMailbox = MailboxType.Inbox,
            currentSelectedEmail = LocalEmailsDataProvider.allEmails.first()
        )
    )
}

@Preview(name = "Tablet Day", group = "Tablet", showSystemUi = true, showBackground = true, device = Devices.TABLET)
@Preview(
    name = "Tablet Night",
    group = "Tablet",
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = Devices.TABLET
)
annotation class TabletPreviews

@Preview(name = "Phone Day", group = "Phone", showSystemUi = true, showBackground = true, device = Devices.PHONE)
@Preview(
    name = "Phone Night",
    group = "Phone",
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = Devices.PHONE
)
annotation class PhonePreviews
