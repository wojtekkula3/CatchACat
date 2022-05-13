package com.wojciechkula.catchacat.di

import com.wojciechkula.catchacat.initializer.AppInitializersContainer
import com.wojciechkula.catchacat.initializer.TimberInitializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class AppInitializerModule {

    @Provides
    fun provideAppInitializersContainer(timberInitializer: TimberInitializer) =
        AppInitializersContainer(timberInitializer)
}