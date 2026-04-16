package com.example.inventory.ui.home

import androidx.lifecycle.ViewModel
import com.example.inventory.data.Item

/**
 * @author runningpig66
 * @date 2026-04-13
 * @time 18:48
 *
 * ViewModel to retrieve all items in the Room database.
 */
class HomeViewModel : ViewModel() {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

/** Ui State for HomeScreen */
data class HomeUiState(val itemList: List<Item> = listOf())
