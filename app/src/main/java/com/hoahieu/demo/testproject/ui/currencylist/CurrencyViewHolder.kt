package com.hoahieu.demo.testproject.ui.currencylist

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hoahieu.demo.testproject.R
import com.hoahieu.demo.testproject.ui.model.CurrencyInfo

class CurrencyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val iconText: TextView = view.findViewById(R.id.cell_currency_icon_text)
    private val name: TextView = view.findViewById(R.id.cell_currency_name)
    private val code: TextView = view.findViewById(R.id.cell_currency_code)

    fun setItem(item: CurrencyInfo) {
        name.text = item.name
        code.text = item.code
        iconText.text = item.code.take(1)
    }
}