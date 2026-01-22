package com.example.lunchtray.model

/**
 * @author runningpig66
 * @date 2026/1/22 周四
 * @time 1:22
 */
data class OrderUiState(
    // Entree Selection
    val entree: MenuItem.EntreeItem? = null,
    val sideDish: MenuItem.SideDishItem? = null,
    val accompaniment: MenuItem.AccompanimentItem? = null,
    val itemTotalPrice: Double = 0.0,
    val orderTax: Double = 0.0,
    val orderTotalPrice: Double = 0.0
)
