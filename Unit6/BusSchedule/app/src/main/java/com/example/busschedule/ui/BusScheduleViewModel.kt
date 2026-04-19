package com.example.busschedule.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.busschedule.data.BusSchedule
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

/**
 * @author runningpig66
 * @date 2026-04-19
 * @time 17:38
 */
class BusScheduleViewModel : ViewModel() {
    // Get example bus schedule
    fun getFullSchedule(): Flow<List<BusSchedule>> = flowOf(
        listOf(BusSchedule(1, "Example Street", 0))
    )

    // Get example bus schedule by stop
    fun getScheduleFor(stopName: String): Flow<List<BusSchedule>> = flowOf(
        listOf(BusSchedule(1, "Example Street", 0))
    )

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                BusScheduleViewModel()
            }
        }
    }
}
