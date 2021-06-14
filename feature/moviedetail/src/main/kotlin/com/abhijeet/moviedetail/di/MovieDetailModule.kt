package com.abhijeet.moviedetail.di

import com.abhijeet.commons.extensions.viewModel
import com.abhijeet.core.di.scopes.FeatureScope
import com.abhijeet.core.repositories.MovieRepository
import com.abhijeet.moviedetail.MovieDetailFragment
import com.abhijeet.moviedetail.MovieDetailViewModel
import dagger.Module
import dagger.Provides

@Module
class MovieDetailModule(private val movieDetailFragment: MovieDetailFragment) {

    @Provides
    @FeatureScope
    fun provideViewModel(
        movieRepository: MovieRepository
    ) = movieDetailFragment.viewModel {
        MovieDetailViewModel(movieRepository)
    }
}