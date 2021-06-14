package com.abhijeet.moviedetail.di

import com.abhijeet.core.di.CoreComponent
import com.abhijeet.core.di.scopes.FeatureScope
import com.abhijeet.moviedetail.MovieDetailFragment
import dagger.Component

@FeatureScope
@Component(
    modules = [MovieDetailModule::class],
    dependencies = [CoreComponent::class]
)
interface MovieDetailComponent {
    fun inject(movieDetailFragment: MovieDetailFragment)
}