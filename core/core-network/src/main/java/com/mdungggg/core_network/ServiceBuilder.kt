package com.mdungggg.core_network

fun <API> buildServiceApi(
    serviceBaseUrl: String,
    serviceApiClass: Class<API>,
    clientBuilder: (ClientBuilder.() -> Unit)? = null,
    converterBuilder: (ConverterBuilder.() -> Unit)? = null
): API {
    return apiBuilder(serviceApiClass) {
        baseUrl = serviceBaseUrl
        client(clientBuilder ?: {})
        converter(converterBuilder ?: {})
    }
}