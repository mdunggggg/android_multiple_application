package com.mindy.jamendo_core_data.data_source

import com.mdunggggg.core_util.Result
import com.mindy.jamendo_core_data.model.Radio
import com.mindy.jamendo_core_data.remote.JamendoApi
import javax.inject.Inject

interface JamendoDataSource {
    suspend fun fetchRadios(
        limit : Int = 10,
        offset : Int = 0,
        order : String = "id_desc"
    ) : Result<List<Radio>, Throwable>
}