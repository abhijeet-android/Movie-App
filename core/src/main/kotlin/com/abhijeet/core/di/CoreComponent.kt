package com.abhijeet.core.di

import android.content.Context
import com.abhijeet.core.data.AppCoroutineDispatchers
import com.abhijeet.core.database.MovieDao
import com.abhijeet.core.di.modules.ContextModule
import com.abhijeet.core.di.modules.DatabaseModule
import com.abhijeet.core.di.modules.DispatcherModule
import com.abhijeet.core.di.modules.NetworkModule
import com.abhijeet.core.network.services.MovieService
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        ContextModule::class,
        NetworkModule::class,
        DatabaseModule::class,
        DispatcherModule::class
    ]
)

interface CoreComponent {

    fun context(): Context

    fun movieService(): MovieService

    fun movieDao(): MovieDao

    fun coroutineDispatchers(): AppCoroutineDispatchers
}