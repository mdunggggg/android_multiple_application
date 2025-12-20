package com.mdunggggg.core_util.network

object NetworkManager {
    private val observers = mutableSetOf<NetworkObserver>()

    var isCurrentOnline: Boolean = true
        private set

    fun addObserver(observer: NetworkObserver) {
        observers.add(observer)
    }

    fun removeObserver(observer: NetworkObserver) {
        observers.remove(observer)
    }

    fun notifyConnectivityChange(isOnline: Boolean) {
        if (isCurrentOnline != isOnline) {
            isCurrentOnline = isOnline
            observers.forEach { observer ->
                observer.onConnectivityChange(isOnline)
            }
        }
    }
}