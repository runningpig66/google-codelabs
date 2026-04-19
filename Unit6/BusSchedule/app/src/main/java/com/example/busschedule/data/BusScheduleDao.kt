package com.example.busschedule.data

import androidx.room3.Dao
import androidx.room3.Query
import kotlinx.coroutines.flow.Flow

/**
 * @author runningpig66
 * @date 2026-04-19
 * @time 22:52
 *
 * Provides access to read/write operations on the schedule table.
 * Used by the view models to format the query results for use in the UI.
 */
@Dao
interface BusScheduleDao {
    @Query(
        """
        SELECT * FROM Schedule
        ORDER BY arrival_time ASC
        """
    )
    fun getAll(): Flow<List<BusSchedule>>

    @Query(
        """
        SELECT * FROM Schedule
        WHERE stop_name = :stopName
        ORDER BY arrival_time ASC
        """
    )
    fun getByStopName(stopName: String): Flow<List<BusSchedule>>
}
