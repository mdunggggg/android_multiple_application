package com.mindy.jamendo_core_data.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class JamendoResponse<T>(

    @Expose
    @SerializedName("headers")
    val headers: Header? = null,
    @Expose
    @SerializedName("results")
    val results: T? = null,
)

data class Header(

    @Expose
    @SerializedName("status")
    val status: String,
    @Expose
    @SerializedName("code")
    val code: Int,
    @Expose
    @SerializedName("error_message")
    val errorMessage: String,
    @Expose
    @SerializedName("warnings")
    val warnings: String,
    @Expose
    @SerializedName("results_count")
    val resultsCount: Int,
)