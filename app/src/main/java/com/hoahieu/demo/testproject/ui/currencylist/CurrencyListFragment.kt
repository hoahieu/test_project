package com.hoahieu.demo.testproject.ui.currencylist

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hoahieu.demo.testproject.R
import com.hoahieu.demo.testproject.ui.model.CurrencyInfo
import com.hoahieu.demo.testproject.ui.safeLaunch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CurrencyListFragment : Fragment(), MenuProvider {
    private val viewModel: CurrencyListViewModel by viewModel()
    private val args: CurrencyListFragmentArgs by navArgs()
    private var queryText: String = ""

    private val recyclerView: RecyclerView
        get() = requireView().findViewById(R.id.currency_list_view)
    private val emptyGroup: View
        get() = requireView().findViewById(R.id.currency_list_empty_group)
    private val emptyMessage: TextView
        get() = requireView().findViewById(R.id.currency_list_empty_message)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_currency_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
        requireActivity().addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun initView() {
        val adapter = CurrencyAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun initViewModel() {
        viewModel.setCurrencyList(args.items.toList())
        safeLaunch {
            viewModel.result
                .collect(::showCurrencies)
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.currency_list_menu, menu)
        val searchItem = menu.findItem(R.id.currency_search)
        (searchItem.actionView as? SearchView)?.run {
            setOnQueryTextListener(
                object : OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        search(query.orEmpty())
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        search(newText.orEmpty())
                        return true
                    }
                }
            )
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun showCurrencies(currencies: List<CurrencyInfo>) {
        when {
            currencies.isNotEmpty() -> emptyGroup.visibility = View.GONE
            queryText.isEmpty() -> {
                emptyGroup.visibility = View.VISIBLE
                emptyMessage.setText(R.string.currency_list_empty_message)
            }

            else -> {
                emptyGroup.visibility = View.VISIBLE
                emptyMessage.setText(R.string.currency_list_search_empty_message)
            }
        }
        (recyclerView.adapter as? CurrencyAdapter)?.run {
            items = currencies
            notifyDataSetChanged()
        }
    }

    private fun search(query: String) {
        queryText = query
        viewModel.search(query)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.currency_search -> true
            else -> false
        }
    }
}