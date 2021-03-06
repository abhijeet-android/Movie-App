package com.abhijeet.core.di.modules

import com.abhijeet.core.data.AppCoroutineDispatchers
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
class DispatcherModule {

    @Singleton
    @Provides
    fun provideCoroutineDispatchers() =
        AppCoroutineDispatchers(
            io = Dispatchers.IO,
            computation = Dispatchers.Default,
            main = Dispatchers.Main
        )
}