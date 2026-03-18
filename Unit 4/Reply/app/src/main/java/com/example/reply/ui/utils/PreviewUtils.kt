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

// 平板竖屏
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

// 平板横屏
@Preview(
    name = "Tablet Landscape Day", group = "Tablet Landscape", showSystemUi = true, showBackground = true,
    device = "spec:parent=pixel_tablet, orientation=landscape"
)
@Preview(
    name = "Tablet Landscape Night",
    group = "Tablet Landscape",
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = "spec:parent=pixel_tablet, orientation=landscape"
)
annotation class TabletLandscapePreviews

// 手机竖屏
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

// 手机横屏
@Preview(
    name = "Phone Landscape Day", group = "Phone Landscape", showSystemUi = true, showBackground = true,
    device = "spec:parent=pixel_5,orientation=landscape"
)
@Preview(
    name = "Phone Landscape Night",
    group = "Phone Landscape",
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = "spec:parent=pixel_5,orientation=landscape"
)
annotation class PhoneLandscapePreviews
