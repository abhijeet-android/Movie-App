package com.abhijeet.movies.di

import androidx.lifecycle.viewModelScope
import com.abhijeet.commons.extensions.viewModel
import com.abhijeet.core.di.scopes.FeatureScope
import com.abhijeet.core.repositories.MovieRepository
import dagger.Module
import dagger.Provides
import com.abhijeet.movies.MoviesPageDataSource
import com.abhijeet.movies.MoviesPageDataSourceFactory
import com.abhijeet.movies.MoviesFragment
import com.abhijeet.movies.MoviesViewModel

@Module
class MoviesModule(
    private val moviesFragment: MoviesFragment
) {

    @Provides
    @FeatureScope
    fun provideViewModel(
        pageDataSourceFactory: MoviesPageDataSourceFactory
    ) = moviesFragment.viewModel {
        MoviesViewModel(pageDataSourceFactory)
    }

    @Provides
    fun provideMovieDataSource(
        moviesRepository: MovieRepository,
        viewModel: MoviesViewModel
    ) = MoviesPageDataSource(moviesRepository, viewModel.viewModelScope)
}