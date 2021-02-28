package com.example.homeawaytestapp.view.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.homeawaytestapp.databinding.SearchMapFragmentBinding
import com.example.homeawaytestapp.model.api.data.search.VenueShort
import com.example.homeawaytestapp.utils.LocationMapper
import com.example.homeawaytestapp.utils.MAIN_LATITUDE
import com.example.homeawaytestapp.utils.MAIN_LONGITUDE
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint

const val VENUES_PARAM = "venues"

@AndroidEntryPoint
class SearchMapFragment : Fragment() {

    private val viewModel: SearchMapViewModel by viewModels()
    private lateinit var binding: SearchMapFragmentBinding
    private val mapView: MapView
        get() = binding.map

    private lateinit var googleMap: GoogleMap

    private val venues by lazy {
        arguments?.getSerializable(VENUES_PARAM) as List<VenueShort>
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SearchMapFragmentBinding.inflate(inflater)
        initMapView(savedInstanceState)
        return binding.root
    }

    private fun initMapView(savedInstanceState: Bundle?) {
        mapView.onCreate(savedInstanceState)
        mapView.onResume()
        try {
            MapsInitializer.initialize(requireActivity().applicationContext)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        mapView.getMapAsync { googleMap ->
            this.googleMap = googleMap
            addVenuesMarkers()
            setupCamera()
        }
    }

    private fun addVenuesMarkers() {
        venues.forEach { venue ->
            val latLng = LocationMapper.mapVenueLocationToLatLng(venue.location)
            val marker = MarkerOptions().position(latLng)
            googleMap.addMarker(marker).tag = venue.id
        }

        googleMap.setOnMarkerClickListener { marker ->
            val id = (marker.tag as String)
            val action = SearchMapFragmentDirections.actionSearchMapFragmentToDetailsFragment(id)
            findNavController().navigate(action)
            false
        }
    }

    private fun setupCamera() {
        val center = LatLng(MAIN_LATITUDE, MAIN_LONGITUDE)
        val cameraPosition = CameraPosition.Builder().target(center).zoom(15f).build()
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }
}