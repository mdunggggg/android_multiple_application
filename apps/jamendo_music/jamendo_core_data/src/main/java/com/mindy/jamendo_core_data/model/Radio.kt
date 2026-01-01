package com.mindy.jamendo_core_data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

//{
//    "id":2,
//    "name":"electro",
//    "dispname":"Electronic Radio",
//    "type":"www",
//    "image":"https:\/\/images.jamendo.com\/new_jamendo_radios\/electro150.jpg"
//}
data class Radio(
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("dispname")
    val dispname: String,
    @Expose
    @SerializedName("type")
    val type: String,
    @Expose
    @SerializedName("image")
    val image: String,
)
