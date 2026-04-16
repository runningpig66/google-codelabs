package com.example.inventory.ui.navigation

/**
 * @author runningpig66
 * @date 2026-04-13
 * @time 18:54
 *
 * Interface to describe the navigation destinations for the app
 */
interface NavigationDestination {
    /** Unique name to define the path for a composable */
    val route: String

    /** String resource id to that contains title to be displayed for the screen. */
    val titleRes: Int
}
