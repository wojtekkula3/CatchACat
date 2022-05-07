package com.wojciechkula.catchacat.data.datasource

import com.wojciechkula.catchacat.data.api.CatFactsApi
import javax.inject.Inject

class RemoteFactsDataSource @Inject constructor(
    private val api: CatFactsApi
) {
    suspend fun fetchFacts() = api.getRandomFacts()
}