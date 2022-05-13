package com.wojciechkula.catchacat.presentation.facts

sealed class FactsViewEvent {
    object ObserveInternetConnection : FactsViewEvent()
    object ShowConnectToInternetImage : FactsViewEvent()
    object ShowConnectToInternetError : FactsViewEvent()
    object ShowGettingOnlineFactsError : FactsViewEvent()
}