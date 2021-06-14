package com.abhijeet.movies.di

import com.abhijeet.core.di.CoreComponent
import com.abhijeet.core.di.scopes.FeatureScope
import dagger.Component
import com.abhijeet.movies.MoviesFragment

@FeatureScope
@Component(
    modules = [MoviesModule::class],
    dependencies = [CoreComponent::class])
interface MoviesComponent {
    fun inject(moviesFragment: MoviesFragment)
}