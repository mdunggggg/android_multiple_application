package com.mdunggggg.core_ui

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
import com.mdunggggg.core_util.network.NetworkManager

abstract class BaseApplication : Application() {
    abstract val needObserverNetworkConnectivity : Boolean
    override fun onCreate() {
        super.onCreate()
        if (needObserverNetworkConnectivity) {
            setupNetworkMonitoring()
        }
    }

    private fun setupNetworkMonitoring() {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                NetworkManager.notifyConnectivityChange(true)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                NetworkManager.notifyConnectivityChange(false)
            }
        }

        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }
}