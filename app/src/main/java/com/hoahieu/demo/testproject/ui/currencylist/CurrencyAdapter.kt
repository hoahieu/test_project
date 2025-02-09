package com.hoahieu.demo.testproject.ui.currencylist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hoahieu.demo.testproject.R
import com.hoahieu.demo.testproject.ui.model.CurrencyInfo

class CurrencyAdapter : RecyclerView.Adapter<CurrencyViewHolder>() {
    var items: List<CurrencyInfo> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cell_currency, parent, false)
        return CurrencyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.setItem(items[position])
    }

    override fun getItemCount() = items.size
}