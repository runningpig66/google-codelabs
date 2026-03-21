package com.example.sports.utils

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

/**
 * @author runningpig66
 * @date 3月21日 周六
 * @time 6:52
 */
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
