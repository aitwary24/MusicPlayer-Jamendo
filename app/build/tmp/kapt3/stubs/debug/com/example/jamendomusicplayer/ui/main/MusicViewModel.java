package com.example.jamendomusicplayer.ui.main;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u0006\u0010\u0017\u001a\u00020\u0014J\u000e\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u000eJ\u001e\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001eR\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006 "}, d2 = {"Lcom/example/jamendomusicplayer/ui/main/MusicViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/example/jamendomusicplayer/data/repository/MusicRepository;", "getTracksUseCase", "Lcom/example/jamendomusicplayer/domain/usecase/GetTracksUseCase;", "sortTracksUseCase", "Lcom/example/jamendomusicplayer/domain/usecase/SortTracksUseCase;", "(Lcom/example/jamendomusicplayer/data/repository/MusicRepository;Lcom/example/jamendomusicplayer/domain/usecase/GetTracksUseCase;Lcom/example/jamendomusicplayer/domain/usecase/SortTracksUseCase;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/jamendomusicplayer/ui/main/MusicUiState;", "allTracks", "", "Lcom/example/jamendomusicplayer/data/model/Track;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "changeSortMode", "", "sortMode", "Lcom/example/jamendomusicplayer/ui/main/SortMode;", "loadTracks", "onTrackSelected", "track", "updatePlaybackState", "isPlaying", "", "currentPos", "", "totalDuration", "app_debug"})
public final class MusicViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.example.jamendomusicplayer.data.repository.MusicRepository repository = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.jamendomusicplayer.domain.usecase.GetTracksUseCase getTracksUseCase = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.jamendomusicplayer.domain.usecase.SortTracksUseCase sortTracksUseCase = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.jamendomusicplayer.ui.main.MusicUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.example.jamendomusicplayer.ui.main.MusicUiState> uiState = null;
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.example.jamendomusicplayer.data.model.Track> allTracks;
    
    public MusicViewModel(@org.jetbrains.annotations.NotNull
    com.example.jamendomusicplayer.data.repository.MusicRepository repository, @org.jetbrains.annotations.NotNull
    com.example.jamendomusicplayer.domain.usecase.GetTracksUseCase getTracksUseCase, @org.jetbrains.annotations.NotNull
    com.example.jamendomusicplayer.domain.usecase.SortTracksUseCase sortTracksUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.example.jamendomusicplayer.ui.main.MusicUiState> getUiState() {
        return null;
    }
    
    public final void loadTracks() {
    }
    
    public final void changeSortMode(@org.jetbrains.annotations.NotNull
    com.example.jamendomusicplayer.ui.main.SortMode sortMode) {
    }
    
    public final void onTrackSelected(@org.jetbrains.annotations.NotNull
    com.example.jamendomusicplayer.data.model.Track track) {
    }
    
    public final void updatePlaybackState(boolean isPlaying, int currentPos, int totalDuration) {
    }
}