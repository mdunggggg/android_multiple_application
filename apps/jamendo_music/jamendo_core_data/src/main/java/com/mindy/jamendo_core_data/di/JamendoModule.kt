package com.mindy.jamendo_core_data.di

import androidx.annotation.Size
import com.mindy.jamendo_core_data.remote.JamendoApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.mindy.jamendo_core_data.BuildConfig
import com.mindy.jamendo_core_data.remote.JamendoEndpoint

@Module
@InstallIn(SingletonComponent::class)
internal object JamendoModule {
    @Provides
    @Singleton
    fun provideJamendoApi() : JamendoApi = JamendoApi.build(JamendoEndpoint.BASE_URL)
}
