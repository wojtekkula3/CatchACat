package com.wojciechkula.catchacat.data.repository

import com.wojciechkula.catchacat.data.datasource.RemoteFactsDataSource
import com.wojciechkula.catchacat.domain.repository.FactRepository
import javax.inject.Inject

class FactRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteFactsDataSource
) : FactRepository {

    override suspend fun fetchFacts() = remoteDataSource.fetchFacts()
}