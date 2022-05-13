package com.wojciechkula.catchacat.di

import android.content.Context
import com.wojciechkula.catchacat.data.dao.FactDao
import com.wojciechkula.catchacat.data.database.LocalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class LocalDatabaseModule {

    @Singleton
    @Provides
    fun provideLocalDatabase(@ApplicationContext applicationContext: Context): LocalDatabase {
        return LocalDatabase.getDatabase(applicationContext)
    }

    @Provides
    fun provideFactDao(database: LocalDatabase): FactDao {
        return database.factsDao()
    }

}