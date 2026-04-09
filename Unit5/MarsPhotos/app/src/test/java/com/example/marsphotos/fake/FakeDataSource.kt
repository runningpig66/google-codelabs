package com.example.marsphotos.fake

import com.example.marsphotos.model.MarsPhoto

/**
 * @author runningpig66
 * @date 2026-04-09
 * @time 22:23
 */
object FakeDataSource {
    const val idOne = "img1"
    const val idTwo = "img2"
    const val imgOne = "url.1"
    const val imgTwo = "url.2"
    val photosList = listOf(
        MarsPhoto(id = idOne, imgSrc = imgOne),
        MarsPhoto(id = idTwo, imgSrc = imgTwo)
    )
}
