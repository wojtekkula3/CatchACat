package com.wojciechkula.catchacat.presentation.facts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.wojciechkula.catchacat.databinding.FragmentFactsBinding
import com.wojciechkula.catchacat.utils.NetworkConnection
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FactsFragment : Fragment() {

    private var _binding: FragmentFactsBinding? = null
    private val binding
        get() = _binding!!

    @Inject
    lateinit var hasNetworkConnection: NetworkConnection

    private val viewModel: FactsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFactsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        observeNetworkConnection()
    }

    private fun observeViewModel() {
        viewModel.viewState.observe(viewLifecycleOwner, ::bindState)
        viewModel.viewEvent.observe(viewLifecycleOwner, ::handleEvents)
    }

    private fun observeNetworkConnection() {
        hasNetworkConnection.observe(viewLifecycleOwner) {
            viewModel.changeNetworkConnectionStatus(it)
        }
    }

    private fun bindState(state: FactsViewState) {
        with(binding) {
            if (state.factList.isNotEmpty()) {
                testLabel.text = "Facts fetched in number of: ${state.factList.size}"
            }

            if (state.factList.isEmpty() && state.hasNetworkConnection) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }

            if (state.hasNetworkConnection) {
                internetConnectionLayout.visibility = View.GONE
            } else {
                internetConnectionLayout.visibility = View.VISIBLE
            }
        }
    }

    private fun handleEvents(event: FactsViewEvent) {
        when (event) {
            FactsViewEvent.ShowNoLocalFactsError -> onError("Connect to the internet!")
            FactsViewEvent.ShowErrorWhileGettingFacts -> onError("Error While getting API Facts :o")
        }
    }

    private fun onError(message: String) {
        Snackbar.make(binding.testLabel, message, Snackbar.LENGTH_LONG)
            .show()
    }
}