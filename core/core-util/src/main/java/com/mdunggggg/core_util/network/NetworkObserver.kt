package com.mdunggggg.core_util.network

interface NetworkObserver {
    fun onConnectivityChange(isOnline: Boolean)
}