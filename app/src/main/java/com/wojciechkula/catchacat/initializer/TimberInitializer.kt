package com.wojciechkula.catchacat.initializer

import android.app.Application
import com.wojciechkula.catchacat.config.AppBuildConfig
import timber.log.Timber
import javax.inject.Inject

class TimberInitializer @Inject constructor() : AppInitializer {

    override fun init(application: Application) {
        if (AppBuildConfig.isDebug) {
            Timber.plant(Timber.DebugTree())
        }
    }
}