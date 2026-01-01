package com.mdunggggg.jamendo_music.screen.home.data

data class TrendingData(
    val thumbnailUrl : String,
    val trendingType : String,
    val trendingTitle : String,
    val trendingDescription : String,
)

val trendingDataDummys = listOf(
    TrendingData(
        thumbnailUrl = "https://hoanghamobile.com/tin-tuc/wp-content/uploads/2023/08/cuc-dep-hinh-nen-thien-nhien.jpg",
        trendingType = "Playlist",
        trendingTitle = "Chill Vibes",
        trendingDescription = "Relaxing tunes to unwind"
    ),
    TrendingData(
        thumbnailUrl = "https://lamdong.gov.vn/sites/snnptnt/gioithieu/dautulamdong/SiteAssets/SitePages/Anh-dep-Da-Lat/73208-canh-dep-da-lat-1570724101-85PJt.jpg",
        trendingType = "Album",
        trendingTitle = "Upbeat Hits",
        trendingDescription = "Get energized with these tracks"
    ),
    TrendingData(
        thumbnailUrl = "https://hoanghamobile.com/tin-tuc/wp-content/uploads/2024/11/tai-hinh-nen-dep-mien-phi-3.jpg",
        trendingType = "Mix",
        trendingTitle = "Morning Boost",
        trendingDescription = "Start your day right"
    )
)