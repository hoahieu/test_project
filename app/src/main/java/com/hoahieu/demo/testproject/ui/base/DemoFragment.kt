package com.hoahieu.demo.testproject.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.hoahieu.demo.testproject.R
import com.hoahieu.demo.testproject.ui.model.DemoUiState
import com.hoahieu.demo.testproject.ui.model.DemoUiState.DataCleared
import com.hoahieu.demo.testproject.ui.model.DemoUiState.DataFetched
import com.hoahieu.demo.testproject.ui.model.DemoUiState.DataGenerated
import com.hoahieu.demo.testproject.ui.model.DemoUiState.Init
import com.hoahieu.demo.testproject.ui.safeLaunch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DemoFragment : Fragment() {
    private val viewModel: DemoViewModel by viewModel()
    private val clearButton: View
        get() = requireView().findViewById(R.id.demo_clear)
    private val generateButton: View
        get() = requireView().findViewById(R.id.demo_generate)
    private val showCryptosButton: View
        get() = requireView().findViewById(R.id.demo_open_cryptos)
    private val showFiatsButton: View
        get() = requireView().findViewById(R.id.demo_open_fiats)
    private val showAllCurrenciesButton: View
        get() = requireView().findViewById(R.id.demo_open_all)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_demo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
    }

    private fun initView() {
        clearButton.setOnClickListener {
            viewModel.clearData()
        }
        generateButton.setOnClickListener {
            viewModel.generateData()
        }
        showCryptosButton.setOnClickListener {
            viewModel.getCryptos()
        }
        showFiatsButton.setOnClickListener {
            viewModel.getFiats()
        }
        showAllCurrenciesButton.setOnClickListener {
            viewModel.getAllCurrencies()
        }
    }

    private fun initViewModel() {
        safeLaunch { viewModel.uiState.collect(::handleState) }
    }

    private fun handleState(state: DemoUiState) {
        when (state) {
            DataCleared -> {
                Toast.makeText(context, "Data cleared", Toast.LENGTH_SHORT).show()
            }

            DataGenerated -> {
                Toast.makeText(context, "Data generated", Toast.LENGTH_SHORT).show()
            }

            is DataFetched -> {
                findNavController().navigate(
                    DemoFragmentDirections.actionDemoFragmentToCurrencyListFragment(
                        state.currencies.toTypedArray()
                    )
                )
                viewModel.clearState()
            }

            Init -> {

            }
        }
    }
}