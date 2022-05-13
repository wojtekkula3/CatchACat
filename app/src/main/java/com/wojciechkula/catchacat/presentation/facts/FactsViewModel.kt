package com.wojciechkula.catchacat.presentation.facts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import com.wojciechkula.catchacat.domain.interctor.GetFactsFromAPIInteractor
import com.wojciechkula.catchacat.domain.interctor.GetFactsFromLocalDSInteractor
import com.wojciechkula.catchacat.extension.newBuilder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FactsViewModel @Inject constructor(
    private val getFactsFromAPIInteractor: GetFactsFromAPIInteractor,
    private val getFactsFromLocalDSInteractor: GetFactsFromLocalDSInteractor,
) : ViewModel() {

    private var _viewState = MutableLiveData<FactsViewState>()
    val viewState: LiveData<FactsViewState>
        get() = _viewState

    private var _viewEvent = LiveEvent<FactsViewEvent>()
    val viewEvent: LiveData<FactsViewEvent>
        get() = _viewEvent

    init {
        _viewState.value = FactsViewState()
        viewModelScope.launch {
            val facts = getFactsFromLocalDSInteractor()
            if (facts.isNotEmpty()) {
                _viewState.value = viewState.newBuilder { copy(factList = facts) }
            } else {
                Timber.d("There is no facts yet in local database")
                _viewEvent.value = FactsViewEvent.ShowConnectToInternetImage
            }
            _viewEvent.value = FactsViewEvent.ObserveInternetConnection
        }
    }

    fun changeNetworkConnectionStatus(hasNetworkConnection: Boolean) {
        _viewState.value = viewState.newBuilder { copy(hasNetworkConnection = hasNetworkConnection) }
        if (_viewState.value?.factList?.isEmpty() == true) {
            loadNewFacts()
        }
    }

    fun loadNewFacts() {
        viewModelScope.launch {
            if (_viewState.value?.hasNetworkConnection == true) {
                _viewState.value = viewState.newBuilder { copy(isLoading = true) }
                val facts = getFactsFromAPIInteractor()
                if (facts.isNotEmpty()) {
                    _viewState.value = viewState.newBuilder { copy(factList = facts) }
                } else {
                    _viewEvent.postValue(FactsViewEvent.ShowGettingOnlineFactsError)
                    Timber.e("Error occurred while getting facts from API")
                }
                _viewState.value = viewState.newBuilder { copy(isLoading = false) }
            } else {
                Timber.d("Cannot fetch new facts because of no internet connection")
                _viewEvent.postValue(FactsViewEvent.ShowConnectToInternetError)
            }
        }
    }
}