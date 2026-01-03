package com.mdunggggg.jamendo_music.di

import com.mdunggggg.jamendo_music.player.controller.MusicControllerManagement
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface MusicControllerEntryPoint {
    fun musicControllerManagement(): MusicControllerManagement
}