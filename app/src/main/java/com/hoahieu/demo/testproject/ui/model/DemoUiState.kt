package com.hoahieu.demo.testproject.ui.model

sealed class DemoUiState {
    data object Init : DemoUiState()
    data object DataGenerated : DemoUiState()
    data object DataCleared : DemoUiState()
    data class DataFetched(val currencies: List<CurrencyInfo>) : DemoUiState()
}