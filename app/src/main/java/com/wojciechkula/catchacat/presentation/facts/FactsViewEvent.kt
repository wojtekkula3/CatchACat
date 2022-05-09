package com.wojciechkula.catchacat.presentation.facts

sealed class FactsViewEvent {
    object ShowNoLocalFactsError : FactsViewEvent()
    object ShowErrorWhileGettingFacts : FactsViewEvent()

}