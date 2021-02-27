package com.example.homeawaytestapp.view.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homeawaytestapp.R
import com.example.homeawaytestapp.databinding.FragmentSearchBinding
import com.example.homeawaytestapp.model.api.data.VenueShort
import com.example.homeawaytestapp.utils.hideKeyboard
import com.example.homeawaytestapp.view.adapter.VenuesSearchAdapter
import com.example.homeawaytestapp.view.map.VENUES_PARAM
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var searchAdapter: VenuesSearchAdapter

    private val searchViewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater)
        initSearchRecyclerView()
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

        binding.searchInput.doOnTextChanged { text, _, _, _ ->
            text ?: return@doOnTextChanged
            searchViewModel.searchVenues(text.toString())
        }

        binding.searchVenueRecyclerView
            .addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    requireActivity().hideKeyboard()
                }
            })

        binding.fab.setOnClickListener {
            val args = bundleOf(VENUES_PARAM to searchAdapter.currentList)
            findNavController().navigate(R.id.action_FirstFragment_to_searchMapFragment, args)
        }
    }

    private fun submitVenuesList(venues: List<VenueShort>) {
        searchAdapter.submitList(venues)
        if (venues.isNotEmpty()) {
            binding.fab.show()
        } else {
            binding.fab.hide()
        }
    }

    private fun initSearchRecyclerView() {
        with(binding.searchVenueRecyclerView) {
            adapter = VenuesSearchAdapter { venue ->
                val action = SearchFragmentDirections.actionFirstFragmentToDetailsFragment(venue.id)
                findNavController().navigate(action)
            }
            layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = RecyclerView.VERTICAL
            }
        }
    }

    private fun errorSnackBar(message: String = "error occurred") {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.searchVenueRecyclerView.clearOnScrollListeners()
    }
}