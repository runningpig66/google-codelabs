package com.example.inventory.ui.item

import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.inventory.InventoryTopAppBar
import com.example.inventory.R
import com.example.inventory.ui.AppViewModelProvider
import com.example.inventory.ui.navigation.NavigationDestination
import com.example.inventory.ui.theme.InventoryTheme
import com.example.inventory.ui.utils.PhonePreviews
import kotlinx.coroutines.launch

/**
 * @author runningpig66
 * @date 2026-04-14
 * @time 10:48
 */
object ItemEditDestination : NavigationDestination {
    override val route = "item_edit"
    override val titleRes = R.string.edit_item_title
    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/{$itemIdArg}"
}

@Composable
fun ItemEditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ItemEditViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val itemUiState = viewModel.itemUiState
    val coroutineScope = rememberCoroutineScope()
    ItemEditScreenContent(
        itemUiState = itemUiState,
        onNavigateUp = onNavigateUp,
        onItemValueChange = viewModel::updateUiState,
        onSaveClick = {
            // Note: If the user rotates the screen very fast, the operation may get cancelled
            // and the item may not be updated in the Database. This is because when config
            // change occurs, the Activity will be recreated and the rememberCoroutineScope will
            // be cancelled - since the scope is bound to composition.
            coroutineScope.launch {
                viewModel.updateItem()
                navigateBack()
            }
        },
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemEditScreenContent(
    itemUiState: ItemUiState,
    onNavigateUp: () -> Unit,
    onItemValueChange: (ItemDetails) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            InventoryTopAppBar(
                title = stringResource(ItemEditDestination.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        },
        modifier = modifier
    ) { innerPadding ->
        ItemEntryBody(
            itemUiState = itemUiState,
            onItemValueChange = onItemValueChange,
            onSaveClick = onSaveClick,
            modifier = Modifier
                .padding(
                    start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                    end = innerPadding.calculateEndPadding(LocalLayoutDirection.current),
                    top = innerPadding.calculateTopPadding()
                )
                .verticalScroll(rememberScrollState())
        )
    }
}

@PhonePreviews
@Composable
fun ItemEditScreenPreview() {
    InventoryTheme {
        ItemEditScreenContent(
            itemUiState = ItemUiState(
                itemDetails = ItemDetails(name = "Item name", price = "10.00", quantity = "5"),
                isEntryValid = true
            ),
            onNavigateUp = {},
            onItemValueChange = {},
            onSaveClick = {}
        )
    }
}
