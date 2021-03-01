package com.example.homeawaytestapp.view.search

import android.os.Bundle
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homeawaytestapp.R
import com.example.homeawaytestapp.databinding.FragmentSearchBinding
import com.example.homeawaytestapp.model.api.data.search.VenueShort
import com.example.homeawaytestapp.utils.hide
import com.example.homeawaytestapp.utils.hideKeyboard
import com.example.homeawaytestapp.view.adapter.VenuesSearchAdapter
import com.example.homeawaytestapp.view.map.VENUES_PARAM
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val searchAdapter by lazy(::initAdapter)
    private lateinit var inputTextWatcher: TextWatcher

    private val searchViewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater)
        initUI(savedInstanceState)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchViewModel.venuesLiveData.observe(viewLifecycleOwner, { result ->
            result.fold({
                submitVenuesList(it)
            }, {
                errorSnackBar()
            })
        })
    }

    private fun submitVenuesList(venues: List<VenueShort>) {
        searchAdapter.submitList(venues)
        if (venues.isNotEmpty()) {
            binding.fab.show()
        } else {
            binding.fab.hide()
        }
        binding.greetingTv.hide()
    }

    private fun initUI(savedInstanceState: Bundle?) {
        initSearchRecyclerView()

        binding.searchVenueRecyclerView
            .addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    requireActivity().hideKeyboard()
                }
            })

        binding.fab.setOnClickListener {
            val args = bundleOf(VENUES_PARAM to searchAdapter.currentList)
            findNavController().navigate(R.id.action_SearchFragment_to_searchMapFragment, args)
        }

        inputTextWatcher = binding.searchInput.doOnTextChanged { text, _, _, _ ->
            if (text.isNullOrEmpty() || savedInstanceState != null) return@doOnTextChanged
            searchViewModel.searchVenues(text.toString())
        }
    }

    private fun initSearchRecyclerView() {
        with(binding.searchVenueRecyclerView) {
            adapter = searchAdapter
            layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = RecyclerView.VERTICAL
            }
        }
    }

    private fun initAdapter() = VenuesSearchAdapter { venue ->
        val action = SearchFragmentDirections.actionSearchFragmentToDetailsFragment(venue.id)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.searchVenueRecyclerView.clearOnScrollListeners()
        binding.searchInput.removeTextChangedListener(inputTextWatcher)
    }

    private fun errorSnackBar(message: String = getString(R.string.default_error_message)) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }
}