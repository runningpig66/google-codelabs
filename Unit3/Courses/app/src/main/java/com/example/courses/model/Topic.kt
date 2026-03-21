package com.example.courses.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * @author runningpig66
 * @date 2025/12/7 周日
 * @time 1:16
 */
data class Topic(
    @StringRes val name: Int,
    val availableCourses: Int,
    @DrawableRes val imageRes: Int
)
