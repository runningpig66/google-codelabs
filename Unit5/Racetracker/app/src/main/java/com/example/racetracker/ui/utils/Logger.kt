package com.example.racetracker.ui.utils

import android.util.Log

/**
 * @author runningpig66
 * @date 2026-04-02
 * @time 17:29
 */
interface Logger {
    fun v(tag: String, msg: String) // Verbose
    fun d(tag: String, msg: String) // Debug
    fun i(tag: String, msg: String) // Info
    fun w(tag: String, msg: String) // Warn
    fun e(tag: String, msg: String) // Error
}

class AndroidLogger : Logger {
    override fun v(tag: String, msg: String) {
        Log.v(tag, msg)
    }

    override fun d(tag: String, msg: String) {
        Log.d(tag, msg)
    }

    override fun i(tag: String, msg: String) {
        Log.i(tag, msg)
    }

    override fun w(tag: String, msg: String) {
        Log.w(tag, msg)
    }

    override fun e(tag: String, msg: String) {
        Log.e(tag, msg)
    }
}

class TestLogger : Logger {
    override fun v(tag: String, msg: String) {
        println("VERBOSE: [$tag] $msg")
    }

    override fun d(tag: String, msg: String) {
        println("DEBUG:   [$tag] $msg")
    }

    override fun i(tag: String, msg: String) {
        println("INFO:    [$tag] $msg")
    }

    override fun w(tag: String, msg: String) {
        println("WARN:    [$tag] $msg")
    }

    override fun e(tag: String, msg: String) {
        println("ERROR:   [$tag] $msg")
    }
}
