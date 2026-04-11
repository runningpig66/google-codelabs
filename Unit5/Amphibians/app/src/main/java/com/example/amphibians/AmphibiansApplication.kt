package com.example.amphibians

import android.app.Application
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.SingletonImageLoader
import coil3.util.DebugLogger
import com.example.amphibians.data.AppContainer
import com.example.amphibians.data.DefaultAppContainer

/**
 * @author runningpig66
 * @date 2026-04-11
 * @time 9:38
 */
class AmphibiansApplication : Application(), SingletonImageLoader.Factory {
    /** AppContainer instance used by the rest of classes to obtain dependencies */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }

    override fun newImageLoader(context: PlatformContext): ImageLoader = ImageLoader(context)
        .newBuilder()
        .logger(DebugLogger())
        .build()
}
