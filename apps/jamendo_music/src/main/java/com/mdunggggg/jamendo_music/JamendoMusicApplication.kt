package com.mdunggggg.jamendo_music

import com.mdunggggg.core_ui.BaseApplication

class JamendoMusicApplication : BaseApplication() {
    override val needObserverNetworkConnectivity: Boolean
        get() = true
}