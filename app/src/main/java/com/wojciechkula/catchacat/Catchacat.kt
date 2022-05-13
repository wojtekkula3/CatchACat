package com.wojciechkula.catchacat

import android.app.Application
import com.wojciechkula.catchacat.initializer.AppInitializersContainer
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class Catchacat : Application() {

    @Inject
    lateinit var initializersContainer: AppInitializersContainer

    override fun onCreate() {
        super.onCreate()
        initializersContainer.init(this)
    }
}