package com.wojciechkula.catchacat.data.datasource

import com.wojciechkula.catchacat.data.dao.FactDao
import com.wojciechkula.catchacat.data.entity.FactEntity
import javax.inject.Inject

class FactLocalDataSource @Inject constructor(
    private val factDao: FactDao
) {

    suspend fun getFacts() = factDao.getProducts()

    suspend fun insertFacts(facts: List<FactEntity>) = factDao.insertProducts(facts)

    suspend fun deleteAllFacts() = factDao.deleteAll()

}