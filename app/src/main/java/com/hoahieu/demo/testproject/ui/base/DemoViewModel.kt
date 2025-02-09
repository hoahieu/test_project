package com.hoahieu.demo.testproject.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hoahieu.demo.testproject.domain.model.CurrencyDomainModel
import com.hoahieu.demo.testproject.domain.usecase.ClearDataUseCase
import com.hoahieu.demo.testproject.domain.usecase.GenerateDataUseCase
import com.hoahieu.demo.testproject.domain.usecase.GetAllCurrenciesUseCase
import com.hoahieu.demo.testproject.domain.usecase.GetCryptoListUseCase
import com.hoahieu.demo.testproject.domain.usecase.GetFiatListUseCase
import com.hoahieu.demo.testproject.ui.DispatcherProvider
import com.hoahieu.demo.testproject.ui.mapper.CurrencyDomainToUiMapper
import com.hoahieu.demo.testproject.ui.model.DemoUiState
import com.hoahieu.demo.testproject.ui.model.DemoUiState.DataCleared
import com.hoahieu.demo.testproject.ui.model.DemoUiState.DataFetched
import com.hoahieu.demo.testproject.ui.model.DemoUiState.DataGenerated
import com.hoahieu.demo.testproject.ui.model.DemoUiState.Init
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DemoViewModel(
    private val clearDataUseCase: ClearDataUseCase,
    private val generateDataUseCase: GenerateDataUseCase,
    private val getFiatListUseCase: GetFiatListUseCase,
    private val getCryptoUseCase: GetCryptoListUseCase,
    private val getAllCurrenciesUseCase: GetAllCurrenciesUseCase,
    private val currencyDomainToUiMapper: CurrencyDomainToUiMapper,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {
    private val _uiState = MutableStateFlow<DemoUiState>(Init)
    val uiState: StateFlow<DemoUiState> = _uiState

    fun clearData() {
        launchIo {
            clearDataUseCase.execute(Unit)
                .onSuccess {
                    _uiState.emit(DataCleared)
                }
        }
    }

    fun generateData() {
        launchIo {
            generateDataUseCase.execute(Unit)
                .onSuccess {
                    _uiState.emit(DataGenerated)
                }
        }
    }

    fun getFiats() {
        launchIo {
            getFiatListUseCase.execute(Unit)
                .onSuccess(::notifyCurrencies)
        }
    }

    fun getCryptos() {
        launchIo {
            getCryptoUseCase.execute(Unit)
                .onSuccess(::notifyCurrencies)
        }
    }

    fun getAllCurrencies() {
        launchIo {
            getAllCurrenciesUseCase.execute(Unit)
                .onSuccess(::notifyCurrencies)
        }
    }

    private fun notifyCurrencies(currencies: List<CurrencyDomainModel>) {
        viewModelScope.launch {
            _uiState.emit(
                DataFetched(
                    currencies.map(currencyDomainToUiMapper::mapToUi)
                )
            )
        }
    }

    private fun launchIo(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch {
            withContext(dispatcherProvider.io, block)
        }
    }

    fun clearState() {
        viewModelScope.launch {
            _uiState.emit(Init)
        }
    }
}