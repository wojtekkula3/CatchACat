package com.wojciechkula.catchacat.domain.interctor

import com.wojciechkula.catchacat.domain.repository.FactRepository
import javax.inject.Inject

class GetFactsFromLocalDSInteractor @Inject constructor(private val repository: FactRepository) {

    suspend operator fun invoke() = repository.getLocalFacts()
}