package com.example.homeawaytestapp.view.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeawaytestapp.model.api.data.details.Venue
import com.example.homeawaytestapp.model.repository.details.DetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val detailsRepository: DetailsRepository
) : ViewModel() {

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean>
        get() = _loadingLiveData

    private val _venueLiveData = MutableLiveData<Result<Venue>>()
    val venueLiveData: LiveData<Result<Venue>>
        get() = _venueLiveData

    fun loadVenue(id: String) {
        viewModelScope.launch {
            _loadingLiveData.value = true

            val result = runCatching { detailsRepository.loadVenueDetails(id) }
            _venueLiveData.value = result

            _loadingLiveData.value = false
        }
    }
}