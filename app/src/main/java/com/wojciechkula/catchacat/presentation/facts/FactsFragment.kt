package com.wojciechkula.catchacat.presentation.facts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.wojciechkula.catchacat.R
import com.wojciechkula.catchacat.databinding.FragmentFactsBinding
import com.wojciechkula.catchacat.presentation.facts.list.FactItem
import com.wojciechkula.catchacat.presentation.facts.list.FactsListAdapter
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

    private val adapter by lazy {
        FactsListAdapter { fact ->
            findNavController().navigate(FactsFragmentDirections.openDetails(fact))
        }
    }

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
        initViews()
        observeViewModel()
    }

    private fun initViews() {
        with(binding) {
            factsRecyclerView.adapter = adapter
            loadNewFactsButton.setOnClickListener {
                viewModel.loadNewFacts()
            }
        }
    }

    private fun observeViewModel() {
        viewModel.viewState.observe(viewLifecycleOwner, ::bindState)
        viewModel.viewEvent.observe(viewLifecycleOwner, ::handleEvents)
    }

    private fun bindState(state: FactsViewState) {
        with(binding) {
            if (state.factList.isNotEmpty()) {
                binding.loadNewFactsButton.visibility = View.VISIBLE
                val factsItem =
                    state.factList.map { factModel ->
                        FactItem(
                            _id = factModel._id,
                            text = factModel.text,
                            updatedAt = factModel.updatedAt
                        )
                    }
                adapter.submitList(factsItem)
            }

            if (state.factList.isEmpty() && !state.hasNetworkConnection) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }

            if (state.isLoading) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        }
    }

    private fun handleEvents(event: FactsViewEvent) {
        when (event) {
            FactsViewEvent.ObserveInternetConnection -> onObserveNetworkConnection()
            FactsViewEvent.ShowConnectToInternetImage -> binding.noInternetLayout.visibility = View.VISIBLE
            FactsViewEvent.ShowConnectToInternetError -> onError(getString(R.string.facts_connect_to_the_internet))
            FactsViewEvent.ShowGettingOnlineFactsError -> onError(getString(R.string.facts_try_again_later))
        }
    }

    private fun onObserveNetworkConnection() {
        hasNetworkConnection.observeForever {
            if (it) {
                binding.noInternetLayout.visibility = View.GONE
            }
            viewModel.changeNetworkConnectionStatus(it)
        }
    }

    private fun onError(message: String) {
        Snackbar.make(binding.progressBar, message, Snackbar.LENGTH_LONG)
            .show()
    }
}