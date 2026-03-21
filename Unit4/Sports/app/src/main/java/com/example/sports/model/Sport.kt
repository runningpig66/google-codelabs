package com.example.sports.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * @author runningpig66
 * @date 3月21日 周六
 * @time 6:01
 * Data model for Sport
 */
data class Sport(
    val id: Int,
    @get:StringRes val titleResourceId: Int,
    @get:StringRes val subtitleResourceId: Int,
    val playerCount: Int,
    val olympic: Boolean,
    @get:DrawableRes val imageResourceId: Int,
    @get:DrawableRes val sportsImageBanner: Int,
    @get:StringRes val sportDetails: Int
)
