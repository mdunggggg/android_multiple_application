package com.mindy.jamendo_core_data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

//{
//    "count": "1",
//    "id": "887202",
//    "position": "10",
//    "name": "Press Record",
//    "duration": "192",
//    "license_ccurl": "http://creativecommons.org/licenses/by-nc-sa/3.0/",
//    "audio": "https://prod-1.storage.jamendo.com/?trackid=887202&format=mp31&from=Tq2lY1pJKdKB3gDniJOmow%3D%3D%7C22AHqEdPql9C2k1gX5Si3Q%3D%3D",
//    "audiodownload": "https://prod-1.storage.jamendo.com/download/track/887202/mp32/",
//    "audiodownload_allowed": true
//}
data class Track(
    @Expose
    @SerializedName("id")
    val id: String,

    @Expose
    @SerializedName("position")
    val position: Int,

    @Expose
    @SerializedName("name")
    val name: String,

    @Expose
    @SerializedName("duration")
    val duration: Int,

    @Expose
    @SerializedName("license_ccurl")
    val licenseCcUrl: String,

    @Expose
    @SerializedName("audio")
    val audio: String,

    @Expose
    @SerializedName("audiodownload")
    val audioDownload: String,

    @Expose
    @SerializedName("audiodownload_allowed")
    val audioDownloadAllowed: Boolean
)
