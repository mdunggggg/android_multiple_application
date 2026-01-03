package com.mindy.jamendo_core_data.repository

import com.mdunggggg.core_util.Result
import com.mindy.jamendo_core_data.model.Album
import com.mindy.jamendo_core_data.model.Radio

interface JamendoRepository {
    suspend fun fetchRadios(
        limit : Int = 10,
        offset : Int = 0,
        order : String = "id_desc"
    ) : Result<List<Radio>, Throwable>

    suspend fun fetchAlbums(
        limit : Int = 10,
        offset : Int = 0,
        order : String = "releasedate_desc"
    ) : Result<List<Album>, Throwable>

    suspend fun fetchAlbum(
        id : String,
    ) : Result<Album, Throwable>
}