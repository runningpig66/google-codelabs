package com.example.amphibians.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author runningpig66
 * @date 2026-04-11
 * @time 9:41
 *
 * Data class that defines an amphibian which includes a name, type, description, and image URL.
 */
@Serializable
data class Amphibian(
    val name: String,
    val type: String,
    val description: String,
    @SerialName("img_src") val imgSrc: String
)
