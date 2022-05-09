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
                _viewEvent.postValue(FactsViewEvent.ShowNoLocalFactsError)
            }
        }
    }

    fun changeNetworkConnectionStatus(hasNetworkConnection: Boolean) {
        _viewState.value = viewState.newBuilder { copy(hasNetworkConnection = hasNetworkConnection) }
        if (hasNetworkConnection && _viewState.value?.factList?.isEmpty() == true) {
            viewModelScope.launch {
                val facts = getFactsFromAPIInteractor()
                if (facts.isNotEmpty()) {
                    _viewState.value = viewState.newBuilder { copy(factList = facts) }
                } else {
                    _viewEvent.postValue(FactsViewEvent.ShowErrorWhileGettingFacts)
                }
            }
        }
    }
}