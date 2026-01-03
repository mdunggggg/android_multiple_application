package com.mdungggg.core_network

import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

class LoggingBuilder internal constructor(){
    var level : LoggingLevel = LoggingLevel.NONE
    fun build() : Interceptor = HttpLoggingInterceptor().apply {
        level = mappingLoggingLevel[this@LoggingBuilder.level] ?: HttpLoggingInterceptor.Level.BASIC
    }
}

var mappingLoggingLevel = mapOf(
    LoggingLevel.NONE to HttpLoggingInterceptor.Level.NONE,
    LoggingLevel.BODY to HttpLoggingInterceptor.Level.BODY,
    LoggingLevel.HEADERS to HttpLoggingInterceptor.Level.HEADERS,
    LoggingLevel.BASIC to HttpLoggingInterceptor.Level.BASIC
)

enum class LoggingLevel {
    NONE,
    BODY,
    HEADERS,
    BASIC
}

