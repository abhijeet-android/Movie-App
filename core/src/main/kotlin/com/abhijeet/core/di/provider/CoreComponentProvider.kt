package com.abhijeet.core.di.provider

import com.abhijeet.core.di.CoreComponent


interface CoreComponentProvider {
    fun provideCoreComponent(): CoreComponent
}