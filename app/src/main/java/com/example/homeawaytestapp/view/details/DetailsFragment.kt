package com.example.homeawaytestapp.view.details

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.example.homeawaytestapp.R
import com.example.homeawaytestapp.databinding.DetailsFragmentBinding
import com.example.homeawaytestapp.model.api.data.details.Venue
import com.example.homeawaytestapp.utils.*
import com.example.homeawaytestapp.view.adapter.VenuePhotosAdapter
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToInt

const val TAG = "VENUES_SCREEN"

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var binding: DetailsFragmentBinding
    private val args: DetailsFragmentArgs by navArgs()
    private val viewModel: DetailsViewModel by viewModels()

    private lateinit var photosAdapter: VenuePhotosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadVenue(args.venueId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailsFragmentBinding.inflate(inflater)
        initPhotosRV()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.venueLiveData.observe(viewLifecycleOwner, { venueResult ->
            venueResult.fold({
                displayVenue(it)
            }, {
                errorMessageToast()
                requireActivity().onBackPressed()
                Log.e(TAG, "error loading venue: ${it.localizedMessage}", it)
                it.printStackTrace()
            })
        })

        viewModel.loadingLiveData.observe(viewLifecycleOwner, { isLoading ->
            if (isLoading) {
                showProgress()
            } else {
                showContent()
            }
        })
    }

    private fun showContent() {
        binding.contentProgressBar.hide()
        binding.appBarLayout.show()
        binding.venueContent.show()
    }

    private fun showProgress() {
        binding.contentProgressBar.show()
        binding.appBarLayout.hide()
        binding.venueContent.hide()
    }

    private fun displayVenue(venue: Venue) {
        initCollapsingToolbar(venue)
        bindGoogleMap(venue)
        bindTitleInfo(venue)
        bindAddress(venue)
        bindContactInfo(venue)
        bindPhotos(venue)
        bindHours(venue)
    }

    private fun bindGoogleMap(venue: Venue) {
        val link = buildString {
            append("$GOOGLE_MAPS_STATIC_BASE_LINK=${venue.location.lat},${venue.location.lng}")
            append("&markers=$MAIN_LATITUDE, $MAIN_LONGITUDE")
            append("&markers=${venue.location.lat}, ${venue.location.lng}")
            append("&size=1000x700")
            append("&zoom=15")
            append("&key=$GOOGLE_MAPS_KEY")
        }

        binding.staticMapImage.loadWithProgressBar(link)
    }

    private fun bindTitleInfo(venue: Venue) {
        binding.venueName.text = venue.name
        binding.categories.text = venue.categories.joinToString(separator = ",") { it.name }
        binding.venueRating.run {
            text = venue.rating.toString()
            try {
                setTextColor(Color.parseColor("#${venue.ratingColor}"))
            } catch (e: Exception) {
            }
        }
        binding.likes.text = venue.likes.summary
        try {
            binding.status.run {
                text = venue.hours.status
                if (venue.hours.isOpen) {
                    setTextColor(resources.getColor(R.color.green))
                } else {
                    setTextColor(resources.getColor(R.color.red))
                }
            }
        } catch (e: Exception) {
            binding.status.hide()
        }
    }

    private fun bindAddress(venue: Venue) {
        binding.address.text = venue.location.formattedAddress.joinToString(separator = "\n") { it }
        val center = AndroidLocation("center").apply {
            latitude = MAIN_LATITUDE
            longitude = MAIN_LONGITUDE
        }

        val destination = AndroidLocation("destination").apply {
            latitude = venue.location.lat
            longitude = venue.location.lng
        }

        binding.distance.text =
            center.distanceTo(destination).roundToInt().toKmOrM(requireContext())
        binding.direction.setOnClickListener {
            val gmmIntentUri =
                Uri.parse("geo:${MAIN_LATITUDE},${MAIN_LONGITUDE}?q=${venue.location.lat},${venue.location.lng}")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            mapIntent.resolveActivity(requireActivity().packageManager)?.let {
                startActivity(mapIntent)
            }
        }
    }

    private fun bindContactInfo(venue: Venue) {
        binding.callIcon.setOnClickListener {
            if (venue.contact.phone.isNotEmpty()) {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${venue.contact.phone}"))
                startActivity(intent)
            } else {
                errorMessage("No phone provided")
            }
        }

        val twitterUserName = venue.contact.twitter
        binding.twitterIcon.setOnClickListener {
            try {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("twitter://user?screen_name=$twitterUserName")
                )
                startActivity(intent)
            } catch (e: Exception) {
                val intent =
                    Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/#!/$twitterUserName"))
                startActivity(intent)
            }
        }

        binding.websiteIcon.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(venue.url))
                startActivity(intent)
            } catch (e: Exception) {
                errorMessage("No web browser provided")
            }
        }

        binding.foursquareIcon.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(venue.canonicalUrl))
            startActivity(intent)
        }
    }

    private fun initPhotosRV() {
        photosAdapter = VenuePhotosAdapter()
        binding.photosRv.adapter = photosAdapter
        binding.photosRv.layoutManager = LinearLayoutManager(requireContext()).apply {
            orientation = RecyclerView.HORIZONTAL
        }
    }

    private fun bindPhotos(venue: Venue) {
        val list = try {
            venue.photos.detailPhotoGroups[0].items
        } catch (e: Exception) {
            emptyList()
        }
        if (list.isNotEmpty()) {
            photosAdapter.submitList(list)
        } else {
            binding.noPhotos.show()
        }
    }

    private fun bindHours(venue: Venue) {
        binding.hours.text = try {
            venue.hours.timeframes.firstOrNull()?.open?.firstOrNull()?.renderedTime.orEmpty()
        } catch (e: Exception) {
            "No info"
        }
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

        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun errorMessage(message: String = "error occurred") {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

    private fun errorMessageToast(message: String = "error occurred") {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_LONG).show()
    }

}