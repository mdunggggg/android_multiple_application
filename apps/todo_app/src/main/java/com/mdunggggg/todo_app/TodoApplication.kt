package com.mdunggggg.todo_app

import com.mdunggggg.core_ui.BaseApplication

class TodoApplication() : BaseApplication() {
    override val needObserverNetworkConnectivity: Boolean
        get() = true

}