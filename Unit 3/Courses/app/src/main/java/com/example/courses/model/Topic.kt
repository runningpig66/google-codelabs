package com.example.courses.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * @date 25/07/13/周日
 * @time 下午 22:47
 */

data class Topic(
    @StringRes val name: Int,
    val availableCourses: Int,
    @DrawableRes val imageRes: Int
)