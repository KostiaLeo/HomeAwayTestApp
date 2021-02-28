package com.example.homeawaytestapp.view.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.homeawaytestapp.model.api.data.search.VenueShort
import com.example.homeawaytestapp.model.repository.search.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : ViewModel() {

    private val _venuesFlow = MutableSharedFlow<String>()

    @ExperimentalCoroutinesApi
    @FlowPreview
    val venuesLiveData: LiveData<Result<List<VenueShort>>> =
        _venuesFlow
            .filter { it.length > 1 }
            .debounce(250)
            .flatMapLatest(searchRepository::searchVenues)
            .asLiveData()

    fun searchVenues(query: String) {
        viewModelScope.launch {
            _venuesFlow.emit(query)
        }
    }
}