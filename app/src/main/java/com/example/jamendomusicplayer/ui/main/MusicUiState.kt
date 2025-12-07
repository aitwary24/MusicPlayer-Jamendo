package com.example.jamendomusicplayer.ui.main

import com.example.jamendomusicplayer.data.model.Track

enum class SortMode {
    NAME_ASC,
    DURATION_ASC
}

data class MusicUiState(
    val isLoading: Boolean = false,
    val tracks: List<Track> = emptyList(),
    val errorMessage: String? = null,
    val sortMode: SortMode = SortMode.NAME_ASC,
    val currentlyPlaying: Track? = null,
    val isPlaying: Boolean = false,
    val currentPositionMs: Int = 0,
    val totalDurationMs: Int = 0,
    val isFromCache: Boolean = false
)
