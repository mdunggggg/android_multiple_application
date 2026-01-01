package com.mindy.jamendo_core_data.data_source

import com.mdunggggg.core_util.Result
import com.mdunggggg.core_util.getResult
import com.mindy.jamendo_core_data.model.Radio
import com.mindy.jamendo_core_data.remote.JamendoApi
import javax.inject.Inject

class JamendoDataRemote @Inject internal constructor(
    private val api : JamendoApi
) : JamendoDataSource {
    override suspend fun fetchRadios(
        limit: Int,
        offset: Int,
        order: String
    ): Result<List<Radio>, Throwable> = getResult {
        api.getRadios(
            limit = limit,
            offset = offset,
            order = order,
        ).results.orEmpty()
    }
}