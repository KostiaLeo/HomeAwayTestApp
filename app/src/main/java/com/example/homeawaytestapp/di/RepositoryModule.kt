package com.example.homeawaytestapp.di

import com.example.homeawaytestapp.model.repository.details.DetailsRepository
import com.example.homeawaytestapp.model.repository.details.DetailsRepositoryImpl
import com.example.homeawaytestapp.model.repository.search.SearchRepository
import com.example.homeawaytestapp.model.repository.search.SearchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindSearchRepository(searchRepositoryImpl: SearchRepositoryImpl): SearchRepository

    @Binds
    abstract fun bindDetailsRepository(detailsRepositoryImpl: DetailsRepositoryImpl): DetailsRepository
}