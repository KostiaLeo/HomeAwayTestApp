package com.example.homeawaytestapp.view.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeawaytestapp.model.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : ViewModel() {
    private val _liveData = MutableLiveData<String>()
    val liveData: LiveData<String>
        get() = _liveData

    init {
        viewModelScope.launch {
            val result = searchRepository.getData()
            _liveData.value = result
        }
    }
}