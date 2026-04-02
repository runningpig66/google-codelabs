package com.example.marsphotos.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

/**
 * @author runningpig66
 * @date 2026-04-02
 * @time 23:18
 */
class MarsViewModel : ViewModel() {
    /**
     * The mutable State that stores the status of the most recent request
     */
    var marsUiState: String by mutableStateOf("")
        private set

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getMarsPhotos()
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [MutableList].
     */
    fun getMarsPhotos() {
        marsUiState = "Set the Mars API status response here!"
    }
}
