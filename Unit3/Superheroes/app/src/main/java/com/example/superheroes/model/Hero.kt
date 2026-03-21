package com.example.superheroes.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * @author runningpig66
 * @date 2025/12/18 周四
 * @time 1:33
 */
data class Hero(
    @StringRes val nameRes: Int,
    @StringRes val descriptionRes: Int,
    @DrawableRes val imageRes: Int
)

data class HeroRow(
    val rowId: Long, // 每一行唯一
    val hero: Hero
)
