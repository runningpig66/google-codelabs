package com.example.inventory.data

import androidx.room3.Entity
import androidx.room3.PrimaryKey

/**
 * @author runningpig66
 * @date 2026-04-13
 * @time 16:58
 *
 * Entity data class represents a single row in the database.
 */
@Entity(tableName = "items")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val price: Double,
    val quantity: Int
)
