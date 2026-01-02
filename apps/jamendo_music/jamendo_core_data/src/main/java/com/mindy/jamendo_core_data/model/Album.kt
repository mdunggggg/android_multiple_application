package com.mindy.jamendo_core_data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.time.LocalDate

//{
//    "id": "622572",
//    "name": "Khuje Nei (Techno) By DJ SB Remix",
//    "releasedate": "2026-01-02",
//    "artist_id": "514730",
//    "artist_name": "djsbremix",
//    "image": "https://usercontent.jamendo.com?type=album&id=622572&width=300&trackid=2296137",
//    "zip": "https://storage.jamendo.com/download/a622572/mp32/",
//    "shorturl": "https://jamen.do/l/a622572",
//    "shareurl": "https://www.jamendo.com/list/a622572",
//    "zip_allowed": true
//}
data class Album(
    @Expose
    @SerializedName("id")
    val  id: String,

    @Expose
    @SerializedName("name")
    val name: String,

    @Expose
    @SerializedName("releasedate")
    val releasedate: String,

    @Expose
    @SerializedName("artist_id")
    val artistId: String,

    @Expose
    @SerializedName("artist_name")
    val artistName: String,

    @Expose
    @SerializedName("image")
    val image: String,

    @Expose
    @SerializedName("zip")
    val zip: String,

    @Expose
    @SerializedName("shorturl")
    val shorturl: String,


    @Expose
    @SerializedName("shareurl")
    val shareurl: String,

    @Expose
    @SerializedName("zip_allowed")
    val zipAllowed: Boolean,

    @Expose
    @SerializedName("tracks")
    val tracks: List<Track> = emptyList()
)