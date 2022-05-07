package com.wojciechkula.catchacat.presentation.facts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wojciechkula.catchacat.data.entity.FactEntity
import com.wojciechkula.catchacat.domain.interctor.GetFactsInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FactsViewModel @Inject constructor(
    private val getFactsInteractor: GetFactsInteractor
) : ViewModel() {

    private var _listOfFacts = MutableLiveData<List<FactEntity>>()
    val listOfFacts: LiveData<List<FactEntity>>
        get() = _listOfFacts

    init {
        viewModelScope.launch {
            val response = getFactsInteractor.invoke()
            if (response.isSuccessful) {
                _listOfFacts.value = getFactsInteractor.invoke().body()
            } else {
                Timber.e(response.errorBody().toString(), "Error while getting facts")
            }
        }
    }
}