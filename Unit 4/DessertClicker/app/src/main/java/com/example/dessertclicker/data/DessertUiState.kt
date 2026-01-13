package com.example.dessertclicker.data

import androidx.annotation.DrawableRes
import com.example.dessertclicker.data.Datasource.dessertList

/**
 * @author runningpig66
 * @date 2026/1/13 周二
 * @time 21:34
 */
data class DessertUiState(
    val revenue: Int = 0,
    val dessertsSold: Int = 0,
    val currentDessertIndex: Int = 0,
    val currentDessertPrice: Int = dessertList[currentDessertIndex].price,
    @get:DrawableRes val currentDessertImageId: Int = dessertList[currentDessertIndex].imageId
)
