package com.example.inventory.data

import kotlinx.coroutines.flow.Flow

/**
 * @author runningpig66
 * @date 2026-04-13
 * @time 17:00
 */
class OfflineItemsRepository(private val itemDao: ItemDao) : ItemsRepository {
    override suspend fun insertItem(item: Item) = itemDao.insert(item)

    override suspend fun updateItem(item: Item) = itemDao.update(item)

    override suspend fun deleteItem(item: Item) = itemDao.delete(item)

    override fun getItemStream(id: Int): Flow<Item?> = itemDao.getItem(id)

    override fun getAllItemsStream(): Flow<List<Item>> = itemDao.getAllItems()
}
