package com.example.jamendomusicplayer.data.remote

import com.example.jamendomusicplayer.BuildConfig
import com.example.jamendomusicplayer.data.model.JamendoTracksResponse
import com.example.jamendomusicplayer.data.model.Track
import com.example.jamendomusicplayer.data.model.toDomain
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

private const val BASE_URL = "https://api.jamendo.com/v3.0/"

interface JamendoApiService {
    suspend fun getPopularTracks(limit: Int = 50): List<Track>
}

class JamendoApiServiceImpl(
    private val clientProvider: KtorClientProvider = KtorClientProvider
) : JamendoApiService {

    override suspend fun getPopularTracks(limit: Int): List<Track> {
        val client = clientProvider.client
        val response: JamendoTracksResponse = client.get(BASE_URL + "tracks") {
            parameter("client_id", BuildConfig.JAMENDO_CLIENT_ID)
            parameter("format", "json")
            parameter("limit", limit)
            parameter("audioformat", "mp32")
            parameter("order", "popularity_total")
            parameter("include", "musicinfo+stats")
        }.body()

        return response.results.map { it.toDomain() }
    }
}
