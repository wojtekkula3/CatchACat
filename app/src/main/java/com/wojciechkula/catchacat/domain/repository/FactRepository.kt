package com.wojciechkula.catchacat.domain.repository

import com.wojciechkula.catchacat.data.entity.FactEntity
import retrofit2.Response

interface FactRepository {

    suspend fun fetchFacts(): Response<List<FactEntity>>
}