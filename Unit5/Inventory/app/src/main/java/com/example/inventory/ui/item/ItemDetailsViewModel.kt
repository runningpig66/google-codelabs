package com.example.inventory.ui.item

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventory.data.ItemsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * @author runningpig66
 * @date 2026-04-13
 * @time 18:42
 *
 * ViewModel to retrieve, update and delete an item from the [ItemsRepository]'s data source.
 */
class ItemDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val itemsRepository: ItemsRepository
) : ViewModel() {

    private val itemId: Int = checkNotNull(savedStateHandle[ItemDetailsDestination.itemIdArg])

    /** Holds the item details ui state. The data is retrieved from [ItemsRepository] and mapped to the UI state. */
    val itemDetailsUiState: StateFlow<ItemDetailsUiState> =
        itemsRepository.getItemStream(itemId)
            .filterNotNull()
            .map { ItemDetailsUiState(outOfStock = it.quantity <= 0, itemDetails = it.toItemDetails()) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ItemDetailsUiState()
            )

    /** Reduces the item quantity by one and update the [ItemsRepository]'s data source. */
    fun reduceQuantityByOne() {
        // 减少商品数量并同步至数据库。减库存属于核心业务逻辑，使用 viewModelScope.launch 可以在 ViewModel 的独立作用域内安全执行。
        // 这种设计实现了“即发即弃”（Fire-and-forget），即使触发操作后页面瞬间被覆盖或销毁，底层的数据更新依然会完整落地。
        // 数据库更新完成后，Room 底层的机制会自动感知表变化，并通过 Flow 将最新状态推送给 UI 层触发重组。
        // 将挂起逻辑封装在内部并对外暴露为普通函数，可以大幅降低 UI 层处理并发和等待逻辑的代码复杂度。
        viewModelScope.launch {
            val currentItem = itemDetailsUiState.value.itemDetails.toItem()
            if (currentItem.quantity > 0) {
                itemsRepository.updateItem(currentItem.copy(quantity = currentItem.quantity - 1))
            }
        }
    }

    /** Deletes the item from the [ItemsRepository]'s data source. */
    suspend fun deleteItem() {
        itemsRepository.deleteItem(itemDetailsUiState.value.itemDetails.toItem())
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

/** UI state for ItemDetailsScreen */
data class ItemDetailsUiState(
    val outOfStock: Boolean = true,
    val itemDetails: ItemDetails = ItemDetails()
)
