package com.mindy.jamendo_core_data.repository

import android.util.Log
import com.mdunggggg.core_util.Result
import com.mdunggggg.core_util.getResult
import com.mindy.jamendo_core_data.model.Album
import com.mindy.jamendo_core_data.model.Radio
import com.mindy.jamendo_core_data.remote.JamendoApi
import javax.inject.Inject

class JamendoRepositoryImpl @Inject internal constructor(
    private val remote : JamendoApi,
) : JamendoRepository {
    override suspend fun fetchRadios(
        limit: Int,
        offset: Int,
        order: String
    ): Result<List<Radio>, Throwable> = getResult {
        val response = remote.getRadios(
            limit = limit,
            offset = offset,
            order = order
        )
        response.results ?: emptyList()
    }

    override suspend fun fetchAlbums(
        limit: Int,
        offset: Int,
        order: String
    ): Result<List<Album>, Throwable> = getResult {
        val response = remote.getAlbums(
            limit = limit,
            offset = offset,
            order = order
        )
        response.results ?: emptyList()
    }

    override suspend fun fetchAlbum(
        id: String,
    ): Result<Album, Throwable> {
        return getResult {
            val response = remote.getAlbumTracks(
                limit = 1,
                offset = 0,
                id = id
            )
            response.results?.firstOrNull() ?: throw Throwable("Album not found")
        }
    }
}