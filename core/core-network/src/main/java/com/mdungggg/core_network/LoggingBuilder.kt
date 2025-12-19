package com.mdungggg.core_network

import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

class LoggingBuilder internal constructor(){
    var level : HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.NONE
    fun build() : Interceptor = HttpLoggingInterceptor().apply {
        level = this@LoggingBuilder.level
    }
}