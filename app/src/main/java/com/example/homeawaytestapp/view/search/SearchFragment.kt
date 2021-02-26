package com.example.homeawaytestapp.view.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homeawaytestapp.databinding.FragmentSearchBinding
import com.example.homeawaytestapp.model.api.data.VenueShort
import com.example.homeawaytestapp.utils.hideKeyboard
import com.example.homeawaytestapp.view.adapter.VenuesSearchAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val searchAdapter by lazy(::initAdapter)

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
        searchViewModel.venuesLiveData.observe(viewLifecycleOwner, {
            if (it.isSuccess) {
                submitVenuesList(it.getOrDefault(emptyList()))
            } else  {
                // todo: error message
            }
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
    }

    private fun submitVenuesList(venues: List<VenueShort>) {
        searchAdapter.submitList(venues)
        if (venues.isNotEmpty()) {
            binding.fab.show()
        } else {
            binding.fab.hide()
        }
    }

    private fun initAdapter(): VenuesSearchAdapter {
        return VenuesSearchAdapter { venue ->
            val action = SearchFragmentDirections.actionFirstFragmentToDetailsFragment(venue.id)
            findNavController().navigate(action)
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

    override fun onDestroyView() {
        super.onDestroyView()
        binding.searchVenueRecyclerView.clearOnScrollListeners()
    }
}