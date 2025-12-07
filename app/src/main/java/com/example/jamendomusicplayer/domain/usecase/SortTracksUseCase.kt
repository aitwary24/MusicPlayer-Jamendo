package com.example.jamendomusicplayer.domain.usecase

import com.example.jamendomusicplayer.data.model.Track
import com.example.jamendomusicplayer.ui.main.SortMode

class SortTracksUseCase {
    operator fun invoke(tracks: List<Track>, sortMode: SortMode): List<Track> {
        return when (sortMode) {
            SortMode.NAME_ASC -> tracks.sortedBy { it.title.lowercase() }
            SortMode.DURATION_ASC -> tracks.sortedBy { it.durationSeconds }
        }
    }
}
