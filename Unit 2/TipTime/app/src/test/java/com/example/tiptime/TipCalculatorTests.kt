package com.example.tiptime

import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.NumberFormat

/**
 * @date 25/06/30/周一
 * @time 下午 18:33
 */

class TipCalculatorTests {
    @Test
    fun calculateTip_20PercentNoRoundup1() {
        val amount = 100.00
        val tipPercent = 20.10
        val expectedTip = NumberFormat.getCurrencyInstance().format(20.1)
        val actualTip = calculateTip(amount, tipPercent, false)
        assertEquals(expectedTip, actualTip)
    }

    @Test
    fun calculateTip_20PercentNoRoundup2() {
        val amount = 100.00
        val tipPercent = 9.10
        val expectedTip = NumberFormat.getCurrencyInstance().format(10)
        val actualTip = calculateTip(amount, tipPercent, true)
        assertEquals(expectedTip, actualTip)
    }
}