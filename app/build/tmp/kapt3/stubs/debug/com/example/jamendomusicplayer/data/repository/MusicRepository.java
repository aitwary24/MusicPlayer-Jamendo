package com.example.jamendomusicplayer.data.repository;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J(\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00f8\u0001\u0002\u00f8\u0001\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0086@\u00f8\u0001\u0002\u00a2\u0006\u0002\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000f\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\n\u0002\b\u0019\u00a8\u0006\u000e"}, d2 = {"Lcom/example/jamendomusicplayer/data/repository/MusicRepository;", "", "apiService", "Lcom/example/jamendomusicplayer/data/remote/JamendoApiService;", "trackDao", "Lcom/example/jamendomusicplayer/data/local/TrackDao;", "(Lcom/example/jamendomusicplayer/data/remote/JamendoApiService;Lcom/example/jamendomusicplayer/data/local/TrackDao;)V", "fetchTracks", "Lkotlin/Result;", "", "Lcom/example/jamendomusicplayer/data/model/Track;", "fetchTracks-IoAF18A", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCachedTracks", "app_debug"})
public final class MusicRepository {
    @org.jetbrains.annotations.NotNull
    private final com.example.jamendomusicplayer.data.remote.JamendoApiService apiService = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.jamendomusicplayer.data.local.TrackDao trackDao = null;
    
    public MusicRepository(@org.jetbrains.annotations.NotNull
    com.example.jamendomusicplayer.data.remote.JamendoApiService apiService, @org.jetbrains.annotations.NotNull
    com.example.jamendomusicplayer.data.local.TrackDao trackDao) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getCachedTracks(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.jamendomusicplayer.data.model.Track>> $completion) {
        return null;
    }
}