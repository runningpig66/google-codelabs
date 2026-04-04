package com.example.marsphotos.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author runningpig66
 * @date 2026-04-03
 * @time 6:29
 */
@Serializable
data class MarsPhoto(
    val id: String,
    @SerialName("img_src")
    val imgSrc: String
)
