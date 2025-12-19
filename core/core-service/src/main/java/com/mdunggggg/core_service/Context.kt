package com.mdunggggg.core_service

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import androidx.core.content.ContextCompat
import androidx.core.content.edit
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

private const val KEY_SERVICE_RUNNING = "isRunning"

fun <T : BaseService> Context.startService(service: Class<T>) {
    ContextCompat.startForegroundService(this, Intent(this, service))
}

fun <T : BaseService> Context.stopService(service: Class<T>) {
    this.stopService(Intent(this, service))
}

fun <T : BaseService> Context.isServiceRunning(service: Class<T>): Boolean {
    return getSharedPreferences(service).getBoolean(KEY_SERVICE_RUNNING, false)
}

fun <T : BaseService> Context.isServiceRunningEvent(service: Class<T>) = callbackFlow {
    val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, _ ->
        trySend(isServiceRunning(service))
    }
    getSharedPreferences(service).registerOnSharedPreferenceChangeListener(listener)
    awaitClose { getSharedPreferences(service).unregisterOnSharedPreferenceChangeListener(listener) }
}

internal fun <T : BaseService> Context.isServiceRunning(service: Class<T>, running: Boolean) {
    getSharedPreferences(service).edit { putBoolean(KEY_SERVICE_RUNNING, running) }
}

private fun <T : BaseService> Context.getSharedPreferences(service: Class<T>): SharedPreferences {
    return getSharedPreferences(service.name, MODE_PRIVATE)
}