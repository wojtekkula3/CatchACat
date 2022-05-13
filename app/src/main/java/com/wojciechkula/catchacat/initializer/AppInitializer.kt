package com.wojciechkula.catchacat.initializer

import android.app.Application

interface AppInitializer {
    fun init(application: Application)
}