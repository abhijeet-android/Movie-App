package com.abhijeet.movielistsample.di

import com.abhijeet.core.di.CoreComponent
import com.abhijeet.movielistsample.MovieListApplication
import com.abhijeet.core.di.scopes.AppScope

import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class]
)
interface AppComponent {

    fun inject(application: MovieListApplication)
}