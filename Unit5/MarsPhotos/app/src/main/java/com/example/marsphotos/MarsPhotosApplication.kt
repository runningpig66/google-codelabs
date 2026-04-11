package com.example.marsphotos

import android.app.Application
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.SingletonImageLoader
import coil3.util.DebugLogger
import com.example.marsphotos.data.AppContainer
import com.example.marsphotos.data.DefaultAppContainer

/**
 * @author runningpig66
 * @date 2026-04-09
 * @time 7:03
 */
class MarsPhotosApplication : Application(), SingletonImageLoader.Factory {
    /** AppContainer instance used by the rest of classes to obtain dependencies */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }

    override fun newImageLoader(context: PlatformContext): ImageLoader =
        ImageLoader(context)
            .newBuilder()
            .logger(DebugLogger())
            .build()
}
