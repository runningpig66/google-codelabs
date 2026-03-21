package com.example.lunchtray.model

import java.text.NumberFormat

/**
 * @author runningpig66
 * @date 2026/1/22 周四
 * @time 1:42
 */
sealed class MenuItem(
    open val name: String,
    open val description: String,
    open val price: Double
) {
    /**
     * Getter method for price.
     * Includes formatting.
     */
    data class EntreeItem(
        override val name: String,
        override val description: String,
        override val price: Double
    ) : MenuItem(name, description, price)

    data class SideDishItem(
        override val name: String,
        override val description: String,
        override val price: Double
    ) : MenuItem(name, description, price)

    data class AccompanimentItem(
        override val name: String,
        override val description: String,
        override val price: Double
    ) : MenuItem(name, description, price)

    /**
     * Getter method for price.
     * Includes formatting.
     */
    fun getFormattedPrice(): String = NumberFormat.getCurrencyInstance().format(price)
}
