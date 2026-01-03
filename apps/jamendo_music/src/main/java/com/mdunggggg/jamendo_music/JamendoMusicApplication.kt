package com.mdunggggg.jamendo_music

import com.mdunggggg.core_ui.BaseApplication
import com.mdunggggg.jamendo_music.di.MusicControllerEntryPoint
import com.mdunggggg.jamendo_music.player.controller.MusicControllerManagement
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class JamendoMusicApplication : BaseApplication() {
    private lateinit var musicControllerManager: MusicControllerManagement

    override val needObserverNetworkConnectivity: Boolean
        get() = true

    override fun onCreate() {
        super.onCreate()
        musicControllerManager = EntryPointAccessors.fromApplication(
            this,
            MusicControllerEntryPoint::class.java
        ).musicControllerManagement()

        musicControllerManager.connect()
    }
}