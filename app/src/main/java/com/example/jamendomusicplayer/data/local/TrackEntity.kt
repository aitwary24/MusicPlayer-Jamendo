package com.example.jamendomusicplayer.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.jamendomusicplayer.data.model.Track

@Entity(tableName = "tracks")
data class TrackEntity(
    @PrimaryKey val id: String,
    val title: String,
    val artist: String,
    val durationSeconds: Int,
    val audioUrl: String,
    val thumbnailUrl: String?
)

fun TrackEntity.toDomain(): Track = Track(
    id = id,
    title = title,
    artist = artist,
    durationSeconds = durationSeconds,
    audioUrl = audioUrl,
    thumbnailUrl = thumbnailUrl
)

fun Track.toEntity(): TrackEntity = TrackEntity(
    id = id,
    title = title,
    artist = artist,
    durationSeconds = durationSeconds,
    audioUrl = audioUrl,
    thumbnailUrl = thumbnailUrl
)
