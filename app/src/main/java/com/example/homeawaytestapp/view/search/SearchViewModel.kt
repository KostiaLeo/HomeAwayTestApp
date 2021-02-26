package com.example.homeawaytestapp.view.search

import androidx.lifecycle.*
import com.example.homeawaytestapp.model.api.data.VenueShort
import com.example.homeawaytestapp.model.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : ViewModel() {

    private val _venuesFlow = MutableSharedFlow<String>()
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