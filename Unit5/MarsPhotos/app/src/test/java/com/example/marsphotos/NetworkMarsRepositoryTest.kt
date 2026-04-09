package com.example.marsphotos

import com.example.marsphotos.data.NetworkMarsPhotosRepository
import com.example.marsphotos.fake.FakeDataSource
import com.example.marsphotos.fake.FakeMarsApiService
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * @author runningpig66
 * @date 2026-04-09
 * @time 22:36
 */
class NetworkMarsRepositoryTest {
    @Test
    fun networkMarsPhotosRepository_getMarsPhotos_verifyPhotoList() = runTest {
        val repository = NetworkMarsPhotosRepository(marsApiService = FakeMarsApiService())
        assertEquals(FakeDataSource.photosList, repository.getMarsPhotos())
    }
}
