package com.example.lunchtray.ui

import androidx.lifecycle.ViewModel
import com.example.lunchtray.model.MenuItem
import com.example.lunchtray.model.MenuItem.AccompanimentItem
import com.example.lunchtray.model.MenuItem.EntreeItem
import com.example.lunchtray.model.MenuItem.SideDishItem
import com.example.lunchtray.model.OrderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat

/**
 * @author runningpig66
 * @date 2026/1/22 周四
 * @time 2:11
 */
class OrderViewModel : ViewModel() {
    private val taxRate = 0.08

    private val _uiState = MutableStateFlow(OrderUiState())
    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()

    fun updateEntree(entree: EntreeItem) {
        updateItemGeneric(entree, _uiState.value.entree) { currentState, newItem ->
            currentState.copy(entree = newItem)
        }
    }

    fun updateSideDish(sideDish: SideDishItem) {
        updateItemGeneric(sideDish, _uiState.value.sideDish) { currentState, newItem ->
            currentState.copy(sideDish = newItem)
        }
    }

    fun updateAccompaniment(accompaniment: AccompanimentItem) {
        // TODO: [架构与并发优化] 解决“外部传参”导致的数据过时风险 (Race Condition)
        //  (待完成 OnJava 线程篇 & Kotlin 协程 Part3 后回顾)
        // 目前 previousItem 是在 update 闭包外部获取的 (_uiState.value.xxx)。
        // 在高并发场景下，从“获取旧值”到“真正执行 update”之间，State 可能已被其他线程修改。
        // 这会导致闭包内计算差价时，使用的是过时的 previousItem，导致金额计算错误。
        //
        // 方案 1 (极致严谨 - 逻辑修正):
        //    修改 updateItemGeneric 签名，不再传递实体对象，而是传递“类型标识” (如 Enum/Sealed Class)。
        //    在 update { state -> ... } 闭包内部，根据类型即时从 state 读取最新的 previousItem。
        //    缺点：在当前 Data Class 结构下，需要写繁琐的 when/if 判断来确定取哪个字段。
        //
        // 方案 2 (降维打击 - 数据结构重构):
        //    重构 OrderUiState，废弃独立字段 (entree, sideDish...)，改用 Map<String, MenuItem> 存储所有选项。
        //    这样只需传递 Key (String)，即可在闭包内通过 state.items[key] 获取最新值。
        //    优点：同时解决了“并发读脏数据”和“代码重复”两个问题，一行代码通用化。
        //
        // 方案 3 (并发工具):
        //    考虑引入 Mutex 互斥锁来保证这一系列操作的原子性。
        updateItemGeneric(accompaniment, _uiState.value.accompaniment) { currentState, newItem ->
            currentState.copy(accompaniment = newItem)
        }
    }

    private fun <T : MenuItem> updateItemGeneric(
        newItem: T,
        previousItem: T?,
        stateReducer: (currentState: OrderUiState, newItem: T) -> OrderUiState
    ) {
        _uiState.update { currentState ->
            val (itemTotalPrice, orderTax, orderTotalPrice) =
                calculateNewPrices(currentState, newItem, previousItem)
            val stateWithItemUpdated = stateReducer(currentState, newItem)
            stateWithItemUpdated.copy(
                itemTotalPrice = itemTotalPrice,
                orderTax = orderTax,
                orderTotalPrice = orderTotalPrice
            )
        }
    }

    private fun calculateNewPrices(
        currentState: OrderUiState,
        newItem: MenuItem,
        previousItem: MenuItem?,
    ): Triple<Double, Double, Double> {
        val previousItemPrice = previousItem?.price ?: 0.0
        // subtract previous item price in case an item of this category was already added.
        val itemTotalPrice = currentState.itemTotalPrice - previousItemPrice + newItem.price
        // recalculate tax
        val orderTax = itemTotalPrice * taxRate
        return Triple(itemTotalPrice, orderTax, itemTotalPrice + orderTax)
    }

    fun resetOrder() {
        _uiState.value = OrderUiState()
    }

    /*fun updateEntree(entree: EntreeItem) {
        val previousEntree = _uiState.value.entree
        updateItem(entree, previousEntree)
    }*/

    /*fun updateSideDish(sideDish: SideDishItem) {
        val previousSideDish = _uiState.value.sideDish
        updateItem(sideDish, previousSideDish)
    }*/

    /*fun updateAccompaniment(accompaniment: AccompanimentItem) {
        val previousAccompaniment = _uiState.value.accompaniment
        updateItem(accompaniment, previousAccompaniment)
    }*/

    /*private fun updateItem(newItem: MenuItem, previousItem: MenuItem?) {
        _uiState.update { currentState ->
            val previousItemPrice = previousItem?.price ?: 0.0
            // subtract previous item price in case an item of this category was already added.
            val itemTotalPrice = currentState.itemTotalPrice - previousItemPrice + newItem.price
            // recalculate tax
            val orderTax = itemTotalPrice * taxRate
            currentState.copy(
                itemTotalPrice = itemTotalPrice,
                orderTax = orderTax,
                orderTotalPrice = itemTotalPrice + orderTax,
                entree = newItem as? EntreeItem ?: currentState.entree,
                sideDish = newItem as? SideDishItem ?: currentState.sideDish,
                accompaniment = newItem as? AccompanimentItem ?: currentState.accompaniment
            )
        }
    }*/
}

fun Double.formatPrice() = NumberFormat.getCurrencyInstance().format(this)
