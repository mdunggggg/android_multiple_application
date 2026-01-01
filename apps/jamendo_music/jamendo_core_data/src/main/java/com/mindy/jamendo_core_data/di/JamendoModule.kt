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
import com.mindy.jamendo_core_data.repository.JamendoRepository
import com.mindy.jamendo_core_data.repository.JamendoRepositoryImpl
import dagger.Binds

@Module
@InstallIn(SingletonComponent::class)
internal object JamendoModule {
    @Provides
    @Singleton
    fun provideJamendoApi() : JamendoApi = JamendoApi.build(JamendoEndpoint.BASE_URL)
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class JamendoRepoModule {
    @Binds
    @Singleton
    abstract fun bindJamendoRepository(
        impl: JamendoRepositoryImpl
    ): JamendoRepository
}