package com.wojciechkula.catchacat.domain.repository

import com.wojciechkula.catchacat.domain.model.FactModel

interface FactRepository {

    suspend fun fetchFacts(): List<FactModel>
    suspend fun getLocalFacts(): List<FactModel>
}