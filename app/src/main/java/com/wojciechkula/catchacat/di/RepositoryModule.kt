package com.wojciechkula.catchacat.di

import com.wojciechkula.catchacat.data.repository.FactRepositoryImpl
import com.wojciechkula.catchacat.domain.repository.FactRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun FactRepository(factRepositoryImpl: FactRepositoryImpl): FactRepository

}