package com.wojciechkula.catchacat.presentation.facts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.wojciechkula.catchacat.databinding.FragmentFactsBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class FactsFragment : Fragment() {

    private var _binding: FragmentFactsBinding? = null
    private val binding
        get() = _binding!!

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
    }

    private fun observeViewModel() {
        viewModel.listOfFacts.observe(viewLifecycleOwner) { list ->
            Timber.d("Fetched list: $list")
        }
    }

}