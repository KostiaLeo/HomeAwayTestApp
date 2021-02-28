package com.example.homeawaytestapp.view.map

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
// that's true that this ViewModel is not used anywhere,
// although it's created in terms of further app updates
// in which we'd like to implement some kind of business logic using ViewModel
@HiltViewModel
class SearchMapViewModel @Inject constructor() : ViewModel()