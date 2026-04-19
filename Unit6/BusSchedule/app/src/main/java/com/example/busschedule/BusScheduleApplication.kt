package com.example.busschedule

import android.app.Application
import com.example.busschedule.data.AppDatabase

/**
 * @author runningpig66
 * @date 2026-04-19
 * @time 23:21
 */
class BusScheduleApplication : Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}
