package com.abhijeet.movielistsample

import android.app.Application
import com.abhijeet.core.di.CoreComponent
import com.abhijeet.core.di.DaggerCoreComponent
import com.abhijeet.core.di.modules.ContextModule
import com.abhijeet.core.di.provider.CoreComponentProvider
import com.abhijeet.movielistsample.di.DaggerAppComponent


class MovieListApplication : Application(), CoreComponentProvider {

    lateinit var coreComponent: CoreComponent

    override fun onCreate() {
        super.onCreate()
        initDependencyInjection()
    }

    private fun initDependencyInjection() {
        coreComponent = DaggerCoreComponent
            .builder()
            .contextModule(ContextModule(this))
            .build()

        DaggerAppComponent
            .builder()
            .coreComponent(coreComponent)
            .build()
            .inject(this)
    }

    override fun provideCoreComponent() = coreComponent
}