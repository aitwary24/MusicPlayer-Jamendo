package com.example.jamendomusicplayer.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JamendoTracksResponse(
    val results: List<TrackDto> = emptyList()
)

@Serializable
data class TrackDto(
    val id: String,
    val name: String,
    @SerialName("artist_name")
    val artistName: String,
    val duration: Int,
    val audio: String,
    val image: String? = null,
    @SerialName("album_image")
    val albumImage: String? = null
)

data class Track(
    val id: String,
    val title: String,
    val artist: String,
    val durationSeconds: Int,
    val audioUrl: String,
    val thumbnailUrl: String?
)

fun TrackDto.toDomain(): Track = Track(
    id = id,
    title = name,
    artist = artistName,
    durationSeconds = duration,
    audioUrl = audio,
    thumbnailUrl = albumImage ?: image
)
