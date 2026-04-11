package com.example.amphibians.network

import com.example.amphibians.model.Amphibian
import retrofit2.http.GET

/**
 * @author runningpig66
 * @date 2026-04-11
 * @time 9:45
 */
interface AmphibiansApiService {
    @GET("amphibians")
    suspend fun getAmphibians(): List<Amphibian>
}
