package com.example.dessertclicker.model

import androidx.annotation.DrawableRes

/**
 * @author runningpig66
 * @date 2026/1/8 周四
 * @time 0:00
 */
data class Dessert(
    @get:DrawableRes val imageId: Int,
    val price: Int,
    val startProductionAmount: Int
)
