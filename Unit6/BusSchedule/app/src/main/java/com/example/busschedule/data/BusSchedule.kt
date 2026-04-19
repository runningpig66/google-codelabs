package com.example.busschedule.data

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.PrimaryKey

/**
 * @author runningpig66
 * @date 2026-04-19
 * @time 17:34
 *
 * Represents a single table in the database. Each row is a separate instance of
 * the BusSchedule class. Each property corresponds to a column.
 * Additionally, an ID is needed as a unique identifier for each row in the database.
 */
@Entity(tableName = "Schedule")
data class BusSchedule(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "stop_name")
    val stopName: String,
    @ColumnInfo(name = "arrival_time")
    val arrivalTimeInMillis: Int
)
