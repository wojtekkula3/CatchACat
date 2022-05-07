package com.wojciechkula.catchacat.domain.interctor

import com.wojciechkula.catchacat.domain.repository.FactRepository
import javax.inject.Inject

class GetFactsInteractor @Inject constructor(private val factRepository: FactRepository) {

    suspend operator fun invoke() = factRepository.fetchFacts()
}