package com.mdunggggg.core_service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

@AndroidEntryPoint
abstract class BaseService : Service() {
    protected val job = SupervisorJob()
    protected val scope = CoroutineScope(job + Dispatchers.Main)

    override fun onCreate() {
        super.onCreate()
        isServiceRunning(this::class.java, true)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
        isServiceRunning(this::class.java, false)
    }
}