package com.example.inventory.ui.item

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.inventory.data.ItemsRepository

/**
 * @author runningpig66
 * @date 2026-04-13
 * @time 18:35
 *
 * ViewModel to retrieve and update an item from the [ItemsRepository]'s data source.
 */
class ItemEditViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    /** Holds current item ui state */
    var itemUiState by mutableStateOf(ItemUiState())

    private val itemId: Int = checkNotNull(savedStateHandle[ItemEditDestination.itemIdArg])

    private fun validateInput(itemDetails: ItemDetails = itemUiState.itemDetails): Boolean {
        return with(itemDetails) {
            name.isNotBlank() && price.isNotBlank() && quantity.isNotBlank()
        }
    }
}
