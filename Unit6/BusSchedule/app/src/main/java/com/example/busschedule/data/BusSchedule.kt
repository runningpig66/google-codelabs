package com.example.busschedule.data

/**
 * @author runningpig66
 * @date 2026-04-19
 * @time 17:34
 */
data class BusSchedule(
    val id: Int,
    val stopName: String,
    val arrivalTimeInMillis: Int
)
