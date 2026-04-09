package com.example.marsphotos.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author runningpig66
 * @date 2026-04-03
 * @time 6:29
 * This data class defines a Mars photo which includes an ID, and the image URL.
 */
@Serializable
data class MarsPhoto(
    val id: String,
    @SerialName(value = "img_src")
    val imgSrc: String
)
