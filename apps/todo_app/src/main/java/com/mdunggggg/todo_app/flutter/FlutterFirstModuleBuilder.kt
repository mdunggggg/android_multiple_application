package com.mdunggggg.todo_app.flutter

import android.app.Activity
import android.content.Context
import com.mdunggggg.core_ui.BaseApplication

class FlutterFirstModuleBuilder(
    application: BaseApplication
) {
    private val flutterModuleHandler = FlutterFirstModuleHandler.getInstance()

    init {
        flutterModuleHandler.preWarmEngine(application)
    }

    fun launchFlutterModule(activity : Activity) {
        val intent = flutterModuleHandler.buildWithCachedEngine(activity)
        activity.startActivity(intent)
    }

    companion object {

        private lateinit var flutterFirstModuleBuilder: FlutterFirstModuleBuilder

        @JvmStatic
        fun getInstance(application: BaseApplication): FlutterFirstModuleBuilder {
            if (!::flutterFirstModuleBuilder.isInitialized) {
                flutterFirstModuleBuilder = FlutterFirstModuleBuilder(application)
            }
            return flutterFirstModuleBuilder
        }
    }
}