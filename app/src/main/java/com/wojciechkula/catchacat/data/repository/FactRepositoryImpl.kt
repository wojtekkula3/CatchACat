package com.wojciechkula.catchacat.data.repository

import com.wojciechkula.catchacat.data.datasource.FactLocalDataSource
import com.wojciechkula.catchacat.data.datasource.FactRemoteDataSource
import com.wojciechkula.catchacat.data.mapper.FactMapper
import com.wojciechkula.catchacat.domain.model.FactModel
import com.wojciechkula.catchacat.domain.repository.FactRepository
import timber.log.Timber
import javax.inject.Inject

class FactRepositoryImpl @Inject constructor(
    private val remoteDataSource: FactRemoteDataSource,
    private val localDataSource: FactLocalDataSource,
    private val mapper: FactMapper
) : FactRepository {

    override suspend fun fetchFacts(): List<FactModel> {
        return try {
            Timber.d("Fetching from API...")
            val response = remoteDataSource.fetchFacts()
            val body = response.body()
            if (body != null) {
                localDataSource.deleteAllFacts()
                localDataSource.insertFacts(body)
            }
            val factList = localDataSource.getFacts().map { factEntity -> mapper.mapToDomain(factEntity) }
            factList
        } catch (e: Exception) {
            Timber.d("Exception while fetching facts", e.message)
            listOf()
        }
    }

    override suspend fun getLocalFacts(): List<FactModel> {
        return localDataSource.getFacts().map { factEntity -> mapper.mapToDomain(factEntity) }
    }
}