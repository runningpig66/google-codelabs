package com.example.inventory

import android.content.Context
import androidx.room3.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.inventory.data.InventoryDatabase
import com.example.inventory.data.Item
import com.example.inventory.data.ItemDao
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * @author runningpig66
 * @date 2026-04-17
 * @time 18:46
 */
@RunWith(AndroidJUnit4::class)
class ItemDaoTest {
    private lateinit var itemDao: ItemDao
    private lateinit var inventoryDatabase: InventoryDatabase
    private var item1 = Item(1, "Apples", 10.0, 20)
    private var item2 = Item(2, "Bananas", 15.0, 97)

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        // 在此函数中，您使用的是内存数据库，而不会将其持久化到磁盘上。为此，您需要使用 inMemoryDatabaseBuilder() 函数。
        // 这样做是因为信息不需要持久化，而是在进程结束时需要被删除。为了便于测试，您正通过 .allowMainThreadQueries() 在主线程中运行 DAO 查询。
        // Using an in-memory database because the information stored here disappears when the process is killed.
        inventoryDatabase = Room.inMemoryDatabaseBuilder(context, InventoryDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        itemDao = inventoryDatabase.itemDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        inventoryDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_insertsItemIntoDB() = runBlocking {
        addOneItemToDb()
        val allItems = itemDao.getAllItems().first()
        assertEquals(item1, allItems[0])
    }

    @Test
    @Throws(Exception::class)
    fun daoGetAllItems_returnsAllItemsFromDB() = runBlocking {
        addTwoItemToDb()
        val allItems = itemDao.getAllItems().first()
        assertEquals(item1, allItems[0])
        assertEquals(item2, allItems[1])
    }

    @Test
    @Throws(Exception::class)
    fun daoUpdateItems_updatesItemsInDB() = runBlocking {
        addTwoItemToDb()
        val item1 = Item(1, "Apples", 15.0, 25)
        val item2 = Item(2, "Bananas", 5.0, 50)
        itemDao.update(item1)
        itemDao.update(item2)
        val allItems = itemDao.getAllItems().first()
        assertEquals(item1, allItems[0])
        assertEquals(item2, allItems[1])
    }

    @Test
    @Throws(Exception::class)
    fun daoDeleteItems_deletesAllItemsFromDB() = runBlocking {
        addTwoItemToDb()
        itemDao.delete(item1)
        itemDao.delete(item2)
        val allItems = itemDao.getAllItems().first()
        assertTrue(allItems.isEmpty())
    }

    @Test
    @Throws(Exception::class)
    fun daoGetItem_returnsItemFromDB() = runBlocking {
        addOneItemToDb()
        val item = itemDao.getItem(1).first()
        assertEquals(item1, item)
    }

    private suspend fun addOneItemToDb() {
        itemDao.insert(item1)
    }

    private suspend fun addTwoItemToDb() {
        itemDao.insert(item1)
        itemDao.insert(item2)
    }
}
