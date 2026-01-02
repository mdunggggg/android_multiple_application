package com.mdunggggg.jamendo_music.dummy

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.mindy.jamendo_core_data.model.Album
import com.mindy.jamendo_core_data.model.Track

object Dummy {
    val dummyTracks = listOf(
        Track(
            id = "887201",
            position = 1,
            name = "Episode 1",
            duration = 120,
            licenseCcUrl = "http://creativecommons.org/licenses/by-nc-sa/3.0/",
            audio = "https://prod-1.storage.jamendo.com/?trackid=887201&format=mp31&from=app-devsite",
            audioDownload = "https://prod-1.storage.jamendo.com/download/track/887201/mp32/",
            audioDownloadAllowed = true
        ),
        Track(
            id = "887202",
            position = 2,
            name = "Episode 2",
            duration = 135,
            licenseCcUrl = "http://creativecommons.org/licenses/by-nc-sa/3.0/",
            audio = "https://prod-1.storage.jamendo.com/?trackid=887202&format=mp31&from=app-devsite",
            audioDownload = "https://prod-1.storage.jamendo.com/download/track/887202/mp32/",
            audioDownloadAllowed = true
        ),
        Track(
            id = "887203",
            position = 3,
            name = "Episode 3",
            duration = 150,
            licenseCcUrl = "http://creativecommons.org/licenses/by-nc-sa/3.0/",
            audio = "https://prod-1.storage.jamendo.com/?trackid=887203&format=mp31&from=app-devsite",
            audioDownload = "https://prod-1.storage.jamendo.com/download/track/887203/mp32/",
            audioDownloadAllowed = false
        ),
        Track(
            id = "887205",
            position = 4,
            name = "Episode 4 Pt. 3",
            duration = 99,
            licenseCcUrl = "http://creativecommons.org/licenses/by-nc-sa/3.0/",
            audio = "https://prod-1.storage.jamendo.com/?trackid=887205&format=mp31&from=app-devsite",
            audioDownload = "https://prod-1.storage.jamendo.com/download/track/887205/mp32/",
            audioDownloadAllowed = true
        )
    )

    val dummyAlbum = Album(
        id = "551122",
        name = "The Podcast Series",
        releasedate = "2024-01-01",
        artistId = "998877",
        artistName = "Creative Artist",
        image = "https://usercontent.jamendo.com/?type=album&id=104336&width=300&trackid=887202",
        zip = "https://prod-1.storage.jamendo.com/download/album/551122/mp32/",
        shorturl = "https://jamen.do/a/551122",
        shareurl = "https://www.jamendo.com/album/551122",
        zipAllowed = true,
        tracks = dummyTracks
    )

    @Composable
    fun dummyAvatar() =  AsyncImage(
        model = "https://scontent.fhan5-11.fna.fbcdn.net/v/t39.30808-1/470194678_2070287133410355_5243510936342359981_n.jpg?stp=dst-jpg_s480x480_tt6&_nc_cat=100&ccb=1-7&_nc_sid=1d2534&_nc_ohc=e9MA-bahipwQ7kNvwEsEgFi&_nc_oc=Adm0Tmr2ivfAXXCLfq1sRgWlJauh4WXyyNY8tWvBUYY5ZTR08OevBqfi0-EiNWLALcv70OtSHeG-c9EOq74HfUCY&_nc_zt=24&_nc_ht=scontent.fhan5-11.fna&_nc_gid=FdImc8h7Z2Xp-UtWRH8MWQ&oh=00_Afq1abmmpWg0pjjLyyjPaZe1UqBq6O6_diNerJs-5MSglA&oe=695C48E2",
        contentDescription = null,
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )

}