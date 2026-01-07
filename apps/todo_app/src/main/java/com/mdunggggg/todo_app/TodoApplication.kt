package com.mdunggggg.todo_app

import com.mdunggggg.core_ui.BaseApplication
import com.mdunggggg.todo_app.flutter.FlutterFirstModuleBuilder

class TodoApplication() : BaseApplication() {
    override val needObserverNetworkConnectivity: Boolean
        get() = true

    override fun onCreate() {
        super.onCreate()
        FlutterFirstModuleBuilder.getInstance(this)
    }

}