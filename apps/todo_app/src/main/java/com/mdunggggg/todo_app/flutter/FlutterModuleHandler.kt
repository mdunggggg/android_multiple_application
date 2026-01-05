package com.mdunggggg.todo_app.flutter

import android.content.Context
import android.content.Intent
import com.mdunggggg.core_ui.BaseApplication

interface FlutterModuleHandler {
    fun preWarmEngine(application: BaseApplication)
    fun buildWithCachedEngine(context: Context) : Intent
    fun getChannelName(): String
    fun getEntryPoint(): String
    fun getEngine() : String
}

class FlutterFirstModuleHandler private constructor() : FlutterModuleHandler {
    private lateinit var flutterEngine : FlutterEngine
    override fun preWarmEngine(application: BaseApplication) {
        TODO("Not yet implemented")
    }

    override fun buildWithCachedEngine(context: Context): Intent {
        TODO("Not yet implemented")
    }

    override fun getChannelName(): String {
        TODO("Not yet implemented")
    }

    override fun getEntryPoint(): String {
        TODO("Not yet implemented")
    }

    override fun getEngine(): String {
        TODO("Not yet implemented")
    }

}