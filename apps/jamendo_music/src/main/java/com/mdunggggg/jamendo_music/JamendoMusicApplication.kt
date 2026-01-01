package com.mdunggggg.jamendo_music

import com.mdunggggg.core_ui.BaseApplication
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class JamendoMusicApplication : BaseApplication() {
    override val needObserverNetworkConnectivity: Boolean
        get() = true
}