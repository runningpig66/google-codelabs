package com.example.busschedule.ui.utils

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

/**
 * @author runningpig66
 * @date 2026-04-19
 * @time 17:36
 */
// 手机竖屏
@Preview(
    name = "Phone Day",
    group = "Phone",
    showSystemUi = true,
    showBackground = true,
    device = Devices.PIXEL_6A
)
@Preview(
    name = "Phone Night",
    group = "Phone",
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = Devices.PIXEL_6A
)
annotation class PhonePreviews
