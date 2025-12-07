package com.example.jamendomusicplayer.ui.main;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0014J\b\u0010\u001c\u001a\u00020\u0019H\u0014J\b\u0010\u001d\u001a\u00020\u0019H\u0014J\u0010\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0010\u0010!\u001a\u00020\u00192\u0006\u0010\"\u001a\u00020#H\u0002J\b\u0010$\u001a\u00020\u0019H\u0002J\b\u0010%\u001a\u00020\u0019H\u0002J\b\u0010&\u001a\u00020\u0019H\u0002J\b\u0010\'\u001a\u00020\u0019H\u0002J\u0010\u0010(\u001a\u00020\u00192\u0006\u0010)\u001a\u00020\u000eH\u0002J\b\u0010*\u001a\u00020\u0019H\u0002J\b\u0010+\u001a\u00020\u0019H\u0002J\b\u0010,\u001a\u00020\u0019H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u000fR\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0012\u001a\u00020\u00138BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0014\u0010\u0015\u00a8\u0006-"}, d2 = {"Lcom/example/jamendomusicplayer/ui/main/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "adapter", "Lcom/example/jamendomusicplayer/ui/main/MusicAdapter;", "binding", "Lcom/example/jamendomusicplayer/databinding/ActivityMainBinding;", "connection", "Landroid/content/ServiceConnection;", "isBound", "", "musicService", "Lcom/example/jamendomusicplayer/player/MusicPlayerService;", "pendingStartIndex", "", "Ljava/lang/Integer;", "seekJob", "Lkotlinx/coroutines/Job;", "viewModel", "Lcom/example/jamendomusicplayer/ui/main/MusicViewModel;", "getViewModel", "()Lcom/example/jamendomusicplayer/ui/main/MusicViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onStart", "onStop", "openPlayerActivity", "track", "Lcom/example/jamendomusicplayer/data/model/Track;", "renderState", "state", "Lcom/example/jamendomusicplayer/ui/main/MusicUiState;", "setupPlaybackControls", "setupRecycler", "setupSortControls", "setupThemeToggle", "startMusicService", "index", "startSeekUpdates", "stopSeekUpdates", "updatePlaybackStateFromService", "app_debug"})
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.jamendomusicplayer.databinding.ActivityMainBinding binding;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy viewModel$delegate = null;
    private com.example.jamendomusicplayer.ui.main.MusicAdapter adapter;
    @org.jetbrains.annotations.Nullable
    private com.example.jamendomusicplayer.player.MusicPlayerService musicService;
    private boolean isBound = false;
    @org.jetbrains.annotations.Nullable
    private kotlinx.coroutines.Job seekJob;
    @org.jetbrains.annotations.Nullable
    private java.lang.Integer pendingStartIndex;
    @org.jetbrains.annotations.NotNull
    private final android.content.ServiceConnection connection = null;
    
    public MainActivity() {
        super();
    }
    
    private final com.example.jamendomusicplayer.ui.main.MusicViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override
    protected void onStart() {
    }
    
    @java.lang.Override
    protected void onStop() {
    }
    
    private final void setupThemeToggle() {
    }
    
    private final void setupRecycler() {
    }
    
    private final void setupSortControls() {
    }
    
    private final void setupPlaybackControls() {
    }
    
    private final void startMusicService(int index) {
    }
    
    private final void openPlayerActivity(com.example.jamendomusicplayer.data.model.Track track) {
    }
    
    private final void renderState(com.example.jamendomusicplayer.ui.main.MusicUiState state) {
    }
    
    private final void startSeekUpdates() {
    }
    
    private final void stopSeekUpdates() {
    }
    
    private final void updatePlaybackStateFromService() {
    }
}