package com.example.jamendomusicplayer.domain.usecase

import com.example.jamendomusicplayer.data.model.Track
import com.example.jamendomusicplayer.data.repository.MusicRepository

class GetTracksUseCase(
    private val repository: MusicRepository
) {

    suspend operator fun invoke(): Result<List<Track>> {
        return repository.fetchTracks()
    }
}
