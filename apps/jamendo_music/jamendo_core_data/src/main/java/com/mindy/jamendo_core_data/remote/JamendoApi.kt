package com.mindy.jamendo_core_data.remote

import com.mdungggg.core_network.LoggingBuilder
import com.mdungggg.core_network.LoggingLevel
import com.mdungggg.core_network.buildServiceApi
import com.mindy.jamendo_core_data.BuildConfig
import com.mindy.jamendo_core_data.data.JamendoRadiosResponse
import com.mindy.jamendo_core_data.model.Radio
import retrofit2.http.GET
import retrofit2.http.Query

internal interface JamendoApi {
    @GET(JamendoEndpoint.RADIOS)
    suspend fun getRadios(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0,
        @Query("order") order: String = "id_desc",
        @Query("client_id") clientId: String = BuildConfig.JAMENDO_CLIENT_ID
    ): JamendoRadiosResponse

    companion object {
        fun build(url: String): JamendoApi {
            return buildServiceApi(url, JamendoApi::class.java, clientBuilder = {
                logging {
                    level = LoggingLevel.BASIC
                }
            })
        }
    }
}