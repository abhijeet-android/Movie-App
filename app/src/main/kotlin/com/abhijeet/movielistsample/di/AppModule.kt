package com.abhijeet.movielistsample.di

import android.content.Context
import com.abhijeet.movielistsample.MovieListApplication
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideContext(application: MovieListApplication): Context = application.applicationContext
}