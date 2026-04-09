package com.example.marsphotos.network

import com.example.marsphotos.model.MarsPhoto
import retrofit2.http.GET

/**
 * @author runningpig66
 * @date 2026-04-03
 * @time 2:22
 * A public interface that exposes the [getPhotos] method.
 */
interface MarsApiService {
    /**
     * Returns a [List] of [MarsPhoto] and this method can be called from a Coroutine.
     * The @GET annotation indicates that the "photos" endpoint will be requested with the GET HTTP method.
     */
    @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto>
}
