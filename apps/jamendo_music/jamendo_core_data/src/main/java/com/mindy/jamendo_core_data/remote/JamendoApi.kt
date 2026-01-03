package com.mindy.jamendo_core_data.remote

import com.mdungggg.core_network.LoggingBuilder
import com.mdungggg.core_network.LoggingLevel
import com.mdungggg.core_network.buildServiceApi
import com.mindy.jamendo_core_data.BuildConfig
import com.mindy.jamendo_core_data.data.JamendoAlbumsResponse
import com.mindy.jamendo_core_data.data.JamendoRadiosResponse
import com.mindy.jamendo_core_data.model.Radio
import okhttp3.Interceptor
import retrofit2.http.GET
import retrofit2.http.Query

internal interface JamendoApi {
    @GET(JamendoEndpoint.RADIOS)
    suspend fun getRadios(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0,
        @Query("order") order: String = "id_desc",
    ): JamendoRadiosResponse

    @GET(JamendoEndpoint.ALBUMS)
    suspend fun getAlbums(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0,
        @Query("order") order: String = "releasedate_desc",
    ) : JamendoAlbumsResponse

    @GET("${JamendoEndpoint.ALBUMS}/${JamendoEndpoint.TRACKS}")
    suspend fun getAlbumTracks(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0,
        @Query("order") order: String = "releasedate_desc",
        @Query("id") id: String,
    ) : JamendoAlbumsResponse

    companion object {
        fun build(url: String): JamendoApi {
            return buildServiceApi(
                url,
                JamendoApi::class.java,
                clientBuilder = {
                    logging {
                        level = LoggingLevel.BODY
                    }
                    addInterceptor { chain ->
                        val originalUrl = chain.request().url
                        val newUrl = originalUrl.newBuilder()
                            .addQueryParameter("client_id", BuildConfig.JAMENDO_CLIENT_ID)
                            .build()
                        val newRequest = chain.request().newBuilder()
                            .url(newUrl)
                            .build()
                        chain.proceed(newRequest)
                    }
                })
        }
    }
}