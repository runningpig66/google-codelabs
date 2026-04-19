package com.example.inventory.data

import kotlinx.coroutines.flow.Flow

/**
 * @author runningpig66
 * @date 2026-04-13
 * @time 17:00
 *
 * Repository that provides insert, update, delete, and retrieve of [Item] from a given data source.
 */
interface ItemsRepository {
    /**
     * Insert item in the data source
     */
    suspend fun insertItem(item: Item)

    /**
     * Update item in the data source
     */
    suspend fun updateItem(item: Item)

    /**
     * Delete item from the data source
     */
    suspend fun deleteItem(item: Item)

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getItemStream(id: Int): Flow<Item?>

    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllItemsStream(): Flow<List<Item>>
}
