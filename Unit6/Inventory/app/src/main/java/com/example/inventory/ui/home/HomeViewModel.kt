package com.example.inventory.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventory.data.Item
import com.example.inventory.data.ItemsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

/**
 * @author runningpig66
 * @date 2026-04-13
 * @time 18:48
 *
 * ViewModel to retrieve all items in the Room database.
 */
class HomeViewModel(itemsRepository: ItemsRepository) : ViewModel() {
    /** Holds home ui state. The list of items are retrieved from [ItemsRepository] and mapped to [HomeUiState] */
    val homeUiState: StateFlow<HomeUiState> =
        itemsRepository.getAllItemsStream().map { HomeUiState(it) }
            .stateIn(
                // 1. scope - viewModelScope 定义了 StateFlow 的生命周期。当 viewModelScope 被取消时， StateFlow 也会随之取消。
                scope = viewModelScope,
                // 2. started - 只有当界面可见时，流水线才应处于活跃状态。SharingStarted.WhileSubscribed() 用于实现这一目的。
                // 如需配置从最后一个订阅者消失到停止共享协程之间的延迟（以毫秒为单位），请将 TIMEOUT_MILLIS 传递给 SharingStarted.WhileSubscribed() 方法。
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                // 3. initialValue - 将状态流的初始值设置为 HomeUiState() 。
                initialValue = HomeUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

/** Ui State for HomeScreen */
data class HomeUiState(val itemList: List<Item> = listOf())
