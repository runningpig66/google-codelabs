package com.example.sports.utils

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

/**
 * @author runningpig66
 * @date 3月21日 周六
 * @time 6:52
 */
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
