package com.mdunggggg.todo_app.flutter

import com.google.gson.Gson
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

class FlutterFirstModuleActivity : FlutterActivity() {
    private var channel: MethodChannel? = null
    private val gson = Gson()

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        channel = MethodChannel(
            flutterEngine.dartExecutor.binaryMessenger,
            METHOD_CHANNEL_NAME
        )
        channel?.setMethodCallHandler(this::handlerChannelMethod)
    }

    private fun handlerChannelMethod(methodCall : MethodCall, resultCallback : MethodChannel.Result) {

    }

    companion object {
        internal  const val ENGINE_ID = "flutter_first_module_engine"
        internal const val METHOD_CHANNEL_NAME = "com.mdunggggg.todo_app/flutter_first_module_channel"

        fun builderWithCachedEngine() =
            CachedEngineIntentBuilder(FlutterFirstModuleActivity::class.java, ENGINE_ID)

    }
}