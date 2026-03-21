package com.example.cupcake.test

import androidx.navigation.NavController
import org.junit.Assert.assertEquals

/**
 * @author runningpig66
 * @date 2026/1/17 周六
 * @time 7:23
 */
fun NavController.assertCurrentRouteName(expectedRouteName: String) {
    assertEquals(expectedRouteName, currentBackStackEntry?.destination?.route)
}
