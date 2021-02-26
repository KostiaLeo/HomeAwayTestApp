package com.example.homeawaytestapp.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.homeawaytestapp.R
import com.example.homeawaytestapp.databinding.DetailsFragmentBinding
import com.example.homeawaytestapp.model.api.data.details.Venue
import com.example.homeawaytestapp.utils.GOOGLE_API_KEY
import com.example.homeawaytestapp.utils.GOOGLE_MAPS_KEY
import com.example.homeawaytestapp.utils.MAIN_LATITUDE
import com.example.homeawaytestapp.utils.MAIN_LONGITUDE
import com.example.homeawaytestapp.view.MainActivity
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var binding: DetailsFragmentBinding
    private val args: DetailsFragmentArgs by navArgs()
    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadVenue(args.venueId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailsFragmentBinding.inflate(inflater)
//        (requireActivity() as MainActivity).setSupportActionBar(binding.toolbar)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.venueLiveData.observe(viewLifecycleOwner, { venueResult ->
            venueResult.fold({
                displayVenue(it)
            }, {
                Snackbar.make(binding.root, "Error occurred", Snackbar.LENGTH_LONG)
            })
        })
    }

    private fun displayVenue(venue: Venue) {
        val link = buildString {
            append("https://maps.googleapis.com/maps/api/staticmap?center=${venue.location.lat},${venue.location.lng}")
            append("&markers=$MAIN_LATITUDE, $MAIN_LONGITUDE")
            append("&markers=${venue.location.lat}, ${venue.location.lng}")
            append("&size=1000x700")
            append("&zoom=15")
            append("&key=$GOOGLE_MAPS_KEY")
        }
        Glide.with(requireContext())
            .load(link)
            .into(binding.staticMapImage)

        initCollapsingToolbar(venue)
    }

    private fun initCollapsingToolbar(venue: Venue) {
        val collapsingToolbar = binding.collapsingToolbarLayout
        var isShow = true
        var scrollRange = -1
        binding.appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
            if (scrollRange == -1) {
                scrollRange = barLayout?.totalScrollRange!!
            }
            if (scrollRange + verticalOffset == 0) {
                binding.collapsingToolbarLayout.title = venue.name
                isShow = true
            } else if (isShow) {
                collapsingToolbar.title = " "
                isShow = false
            }
        })
    }
}