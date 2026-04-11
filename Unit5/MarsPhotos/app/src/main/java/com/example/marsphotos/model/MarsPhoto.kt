package com.example.marsphotos.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author runningpig66
 * @date 2026-04-03
 * @time 6:29
 *
 * This data class defines a Mars photo which includes an ID, and the image URL.
 *
 * [
 *   {
 *     "id": "424905",
 *     "img_src": "https://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000MR0044631300503690E01_DXXX.jpg"
 *   },
 *   {
 *     "id": "424906",
 *     "img_src": "https://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000ML0044631300305227E03_DXXX.jpg"
 *   }
 * ]
 */
@Serializable
data class MarsPhoto(
    val id: String,
    @SerialName(value = "img_src")
    val imgSrc: String
)
