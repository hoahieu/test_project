package com.hoahieu.demo.testproject.ui.currencylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hoahieu.demo.testproject.ui.CurrencyInfoFilter
import com.hoahieu.demo.testproject.ui.DispatcherProvider
import com.hoahieu.demo.testproject.ui.model.CurrencyInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CurrencyListViewModel(
    private val currencyInfoFilter: CurrencyInfoFilter,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {
    private var data: List<CurrencyInfo> = emptyList()
    private val searchTerms = MutableStateFlow("")
    private val _result: MutableStateFlow<List<CurrencyInfo>> = MutableStateFlow(emptyList())
    val result: StateFlow<List<CurrencyInfo>> = _result

    fun setCurrencyList(items: List<CurrencyInfo>) {
        data = items
        viewModelScope.launch {
            searchTerms.collect(::filter)
        }
    }

    fun search(query: String) {
        viewModelScope.launch {
            searchTerms.emit(query)
        }
    }

    private fun filter(query: String) {
        viewModelScope.launch {
            withContext(dispatcherProvider.default) {
                _result.emit(
                    currencyInfoFilter.filter(data, query)
                )
            }
        }
    }
}