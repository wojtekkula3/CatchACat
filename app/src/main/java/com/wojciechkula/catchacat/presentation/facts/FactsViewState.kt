package com.wojciechkula.catchacat.presentation.facts

import com.wojciechkula.catchacat.domain.model.FactModel

data class FactsViewState(
    val factList: List<FactModel> = listOf(),
    val hasNetworkConnection: Boolean = false
)