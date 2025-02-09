package com.hoahieu.demo.testproject.ui

import com.hoahieu.demo.testproject.ui.model.CurrencyInfo

class CurrencyInfoFilter {
    fun filter(infos: List<CurrencyInfo>, query: String): List<CurrencyInfo> {
        return when {
            infos.isEmpty() -> emptyList()
            query.isEmpty() || query.isBlank() -> infos
            query.startsWith(' ') -> {
                infos.filter { it.name.lowercase().contains(query.lowercase()) }
            }

            else -> {
                infos.filter {
                    it.name.lowercase().startsWith(query.lowercase()) ||
                            it.code.lowercase().startsWith(query.lowercase())
                }
            }
        }
    }
}