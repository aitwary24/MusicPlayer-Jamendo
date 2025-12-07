package com.example.jamendomusicplayer.data.repository

import com.example.jamendomusicplayer.data.local.TrackDao
import com.example.jamendomusicplayer.data.local.toDomain
import com.example.jamendomusicplayer.data.local.toEntity
import com.example.jamendomusicplayer.data.model.Track
import com.example.jamendomusicplayer.data.remote.JamendoApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MusicRepository(
    private val apiService: JamendoApiService,
    private val trackDao: TrackDao
) {

    /**
     * Fetch tracks from API, save to local DB, and return.
     * If network fails, fall back to cached local tracks.
     */
    suspend fun fetchTracks(): Result<List<Track>> = withContext(Dispatchers.IO) {
        return@withContext try {
            val tracks = apiService.getPopularTracks()
            trackDao.clearTracks()
            trackDao.insertTracks(tracks.map { it.toEntity() })
            Result.success(tracks)
        } catch (e: Exception) {
            // Try local cache
            val cached = trackDao.getAllTracks().map { it.toDomain() }
            if (cached.isNotEmpty()) {
                Result.success(cached)
            } else {
                Result.failure(e)
            }
        }
    }

    suspend fun getCachedTracks(): List<Track> = withContext(Dispatchers.IO) {
        trackDao.getAllTracks().map { it.toDomain() }
    }
}
