package com.mindy.jamendo_core_data.di

import com.mindy.jamendo_core_data.data_source.JamendoDataRemote
import com.mindy.jamendo_core_data.data_source.JamendoDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class JamendoDataSourceModules {
    @Binds
    @Singleton
    abstract fun bindJamendoDataRemote(
        jamendoDataRemote: JamendoDataRemote
    ): JamendoDataSource
}