package com.mdunggggg.todo_app.flutter

import android.content.Context
import android.content.Intent
import com.mdunggggg.core_ui.BaseApplication
import io.flutter.FlutterInjector
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.FlutterEngineGroup
import io.flutter.embedding.engine.dart.DartExecutor

interface FlutterModuleHandler {
    fun preWarmEngine(application: BaseApplication)
    fun buildWithCachedEngine(context: Context) : Intent
}

class FlutterFirstModuleHandler private constructor() : FlutterModuleHandler {
    private lateinit var flutterEngine : FlutterEngineGroup

    override fun preWarmEngine(application: BaseApplication) {
        flutterEngine = FlutterEngineGroup(application)
        val flutterFirstModuleExampleEntryPoint = DartExecutor.DartEntrypoint(
            FlutterInjector.instance().flutterLoader().findAppBundlePath(),
            ENTRY_POINT
        )
        FlutterEngineCache.getInstance().put(
            FlutterFirstModuleActivity.ENGINE_ID,
            flutterEngine.createAndRunEngine(
                application,
                flutterFirstModuleExampleEntryPoint
            )
        )

    }

    override fun buildWithCachedEngine(context: Context): Intent {
        return  FlutterFirstModuleActivity.builderWithCachedEngine().build(context)
    }


    companion object {
        private const val ENTRY_POINT = "firstModuleExampleMain"

        private lateinit var instance: FlutterFirstModuleHandler
        fun getInstance(): FlutterFirstModuleHandler {
            if (!::instance.isInitialized) {
                instance = FlutterFirstModuleHandler()
            }
            return instance
        }

    }

}