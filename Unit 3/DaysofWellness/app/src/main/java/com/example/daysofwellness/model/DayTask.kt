package com.example.daysofwellness.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * @author runningpig66
 * @date 2026/1/5 周一
 * @time 4:09
 */
data class DayTask(
    val day: Int,
    @get:StringRes val titleRes: Int,
    @get:StringRes val descriptionRes: Int,
    @get:DrawableRes val imageRes: Int
)
