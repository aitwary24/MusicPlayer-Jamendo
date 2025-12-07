package com.example.jamendomusicplayer.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jamendomusicplayer.data.model.Track
import com.example.jamendomusicplayer.data.repository.MusicRepository
import com.example.jamendomusicplayer.domain.usecase.GetTracksUseCase
import com.example.jamendomusicplayer.domain.usecase.SortTracksUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MusicViewModel(
    private val repository: MusicRepository,
    private val getTracksUseCase: GetTracksUseCase,
    private val sortTracksUseCase: SortTracksUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MusicUiState())
    val uiState: StateFlow<MusicUiState> = _uiState

    private var allTracks: List<Track> = emptyList()

    fun loadTracks() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            val result = getTracksUseCase()
            result
                .onSuccess { tracks ->
                    allTracks = tracks
                    val sorted = sortTracksUseCase(tracks, _uiState.value.sortMode)
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            tracks = sorted,
                            errorMessage = null,
                            isFromCache = false
                        )
                    }
                }
                .onFailure { error ->
                    // Try to show cached tracks
                    val cached = repository.getCachedTracks()
                    if (cached.isNotEmpty()) {
                        allTracks = cached
                        val sorted = sortTracksUseCase(cached, _uiState.value.sortMode)
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                tracks = sorted,
                                errorMessage = error.localizedMessage ?: "Network error - showing offline data",
                                isFromCache = true
                            )
                        }
                    } else {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = error.localizedMessage ?: "Something went wrong"
                            )
                        }
                    }
                }
        }
    }

    fun changeSortMode(sortMode: SortMode) {
        _uiState.update { state ->
            val sorted = sortTracksUseCase(allTracks, sortMode)
            state.copy(
                sortMode = sortMode,
                tracks = sorted
            )
        }
    }

    fun onTrackSelected(track: Track) {
        _uiState.update { it.copy(currentlyPlaying = track) }
    }

    fun updatePlaybackState(
        isPlaying: Boolean,
        currentPos: Int,
        totalDuration: Int
    ) {
        _uiState.update {
            it.copy(
                isPlaying = isPlaying,
                currentPositionMs = currentPos,
                totalDurationMs = totalDuration
            )
        }
    }
}
