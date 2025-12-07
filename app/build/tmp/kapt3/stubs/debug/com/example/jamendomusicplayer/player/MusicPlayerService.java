package com.example.jamendomusicplayer.player;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001c\u0018\u0000 K2\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0002KLB\u0005\u00a2\u0006\u0002\u0010\u0005J\b\u0010\'\u001a\u00020(H\u0002J\b\u0010)\u001a\u00020*H\u0002J\u0012\u0010+\u001a\u00020*2\b\u0010,\u001a\u0004\u0018\u00010-H\u0002J\u0012\u0010.\u001a\u00020/2\b\u00100\u001a\u0004\u0018\u000101H\u0016J\u0012\u00102\u001a\u00020*2\b\u00103\u001a\u0004\u0018\u00010\u0018H\u0016J\b\u00104\u001a\u00020*H\u0016J\b\u00105\u001a\u00020*H\u0016J\"\u00106\u001a\u00020\u00142\b\u00103\u001a\u0004\u0018\u00010\u00182\u0006\u00107\u001a\u00020\u000b2\u0006\u00108\u001a\u00020\u000bH\u0016J\u0012\u00109\u001a\u00020*2\b\u00103\u001a\u0004\u0018\u00010\u0018H\u0016J\"\u0010:\u001a\u00020\u000b2\b\u00100\u001a\u0004\u0018\u0001012\u0006\u0010;\u001a\u00020\u000b2\u0006\u0010<\u001a\u00020\u000bH\u0016J\u000e\u0010=\u001a\u00020*2\u0006\u0010>\u001a\u00020\u000bJ\u0006\u0010?\u001a\u00020*J\u0006\u0010@\u001a\u00020*J\u0010\u0010A\u001a\u00020*2\u0006\u0010,\u001a\u00020-H\u0002J\b\u0010B\u001a\u00020*H\u0002J\u000e\u0010C\u001a\u00020*2\u0006\u0010D\u001a\u00020\u000bJ\u001c\u0010\u001f\u001a\u00020*2\f\u0010E\u001a\b\u0012\u0004\u0012\u00020\u00100\u001c2\u0006\u0010F\u001a\u00020\u000bJ\b\u0010G\u001a\u00020*H\u0002J\b\u0010H\u001a\u00020*H\u0002J\u0006\u0010I\u001a\u00020*J\b\u0010J\u001a\u00020*H\u0002R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u00060\tR\u00020\u0000X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\"\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\u0010\f\u001a\u0004\u0018\u00010\u0010@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0015\u001a\u00020\u00142\u0006\u0010\f\u001a\u00020\u0014@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082.\u00a2\u0006\u0002\n\u0000R \u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00100\u001cX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010%\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u000f\u00a8\u0006M"}, d2 = {"Lcom/example/jamendomusicplayer/player/MusicPlayerService;", "Landroid/app/Service;", "Landroid/media/MediaPlayer$OnPreparedListener;", "Landroid/media/MediaPlayer$OnCompletionListener;", "Landroid/media/MediaPlayer$OnErrorListener;", "()V", "albumBitmap", "Landroid/graphics/Bitmap;", "binder", "Lcom/example/jamendomusicplayer/player/MusicPlayerService$MusicPlayerBinder;", "currentIndex", "", "<set-?>", "currentPosition", "getCurrentPosition", "()I", "Lcom/example/jamendomusicplayer/data/model/Track;", "currentTrack", "getCurrentTrack", "()Lcom/example/jamendomusicplayer/data/model/Track;", "", "isPlaying", "()Z", "mediaPlayer", "Landroid/media/MediaPlayer;", "mediaSession", "Landroid/support/v4/media/session/MediaSessionCompat;", "playlist", "", "getPlaylist", "()Ljava/util/List;", "setPlaylist", "(Ljava/util/List;)V", "progressJob", "Lkotlinx/coroutines/Job;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "totalDuration", "getTotalDuration", "buildNotification", "Landroid/app/Notification;", "createNotificationChannel", "", "loadAlbumArtAsync", "url", "", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCompletion", "mp", "onCreate", "onDestroy", "onError", "what", "extra", "onPrepared", "onStartCommand", "flags", "startId", "playAt", "index", "playNext", "playPrevious", "prepareAndPlay", "releasePlayer", "seekTo", "positionMs", "tracks", "startIndex", "startProgressUpdates", "stopProgressUpdates", "togglePlayPause", "updateNotification", "Companion", "MusicPlayerBinder", "app_debug"})
public final class MusicPlayerService extends android.app.Service implements android.media.MediaPlayer.OnPreparedListener, android.media.MediaPlayer.OnCompletionListener, android.media.MediaPlayer.OnErrorListener {
    @org.jetbrains.annotations.NotNull
    private final com.example.jamendomusicplayer.player.MusicPlayerService.MusicPlayerBinder binder = null;
    @org.jetbrains.annotations.Nullable
    private android.media.MediaPlayer mediaPlayer;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.CoroutineScope scope = null;
    @org.jetbrains.annotations.Nullable
    private kotlinx.coroutines.Job progressJob;
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.example.jamendomusicplayer.data.model.Track> playlist;
    private int currentIndex = -1;
    @org.jetbrains.annotations.Nullable
    private com.example.jamendomusicplayer.data.model.Track currentTrack;
    private boolean isPlaying = false;
    private int currentPosition = 0;
    private int totalDuration = 0;
    private android.support.v4.media.session.MediaSessionCompat mediaSession;
    @org.jetbrains.annotations.Nullable
    private android.graphics.Bitmap albumBitmap;
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String CHANNEL_ID = "music_playback_channel";
    private static final int NOTIFICATION_ID = 1001;
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String ACTION_TOGGLE = "ACTION_TOGGLE";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String ACTION_NEXT = "ACTION_NEXT";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String ACTION_PREV = "ACTION_PREV";
    @org.jetbrains.annotations.NotNull
    public static final com.example.jamendomusicplayer.player.MusicPlayerService.Companion Companion = null;
    
    public MusicPlayerService() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.jamendomusicplayer.data.model.Track> getPlaylist() {
        return null;
    }
    
    public final void setPlaylist(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.jamendomusicplayer.data.model.Track> p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.example.jamendomusicplayer.data.model.Track getCurrentTrack() {
        return null;
    }
    
    public final boolean isPlaying() {
        return false;
    }
    
    public final int getCurrentPosition() {
        return 0;
    }
    
    public final int getTotalDuration() {
        return 0;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public android.os.IBinder onBind(@org.jetbrains.annotations.Nullable
    android.content.Intent intent) {
        return null;
    }
    
    @java.lang.Override
    public void onCreate() {
    }
    
    @java.lang.Override
    public void onDestroy() {
    }
    
    @java.lang.Override
    public void onPrepared(@org.jetbrains.annotations.Nullable
    android.media.MediaPlayer mp) {
    }
    
    @java.lang.Override
    public void onCompletion(@org.jetbrains.annotations.Nullable
    android.media.MediaPlayer mp) {
    }
    
    @java.lang.Override
    public boolean onError(@org.jetbrains.annotations.Nullable
    android.media.MediaPlayer mp, int what, int extra) {
        return false;
    }
    
    public final void setPlaylist(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.jamendomusicplayer.data.model.Track> tracks, int startIndex) {
    }
    
    public final void playAt(int index) {
    }
    
    public final void togglePlayPause() {
    }
    
    public final void seekTo(int positionMs) {
    }
    
    public final void playNext() {
    }
    
    public final void playPrevious() {
    }
    
    private final void prepareAndPlay(java.lang.String url) {
    }
    
    private final void releasePlayer() {
    }
    
    private final void startProgressUpdates() {
    }
    
    private final void stopProgressUpdates() {
    }
    
    private final void createNotificationChannel() {
    }
    
    private final void loadAlbumArtAsync(java.lang.String url) {
    }
    
    private final android.app.Notification buildNotification() {
        return null;
    }
    
    private final void updateNotification() {
    }
    
    @java.lang.Override
    public int onStartCommand(@org.jetbrains.annotations.Nullable
    android.content.Intent intent, int flags, int startId) {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/example/jamendomusicplayer/player/MusicPlayerService$Companion;", "", "()V", "ACTION_NEXT", "", "ACTION_PREV", "ACTION_TOGGLE", "CHANNEL_ID", "NOTIFICATION_ID", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/example/jamendomusicplayer/player/MusicPlayerService$MusicPlayerBinder;", "Landroid/os/Binder;", "(Lcom/example/jamendomusicplayer/player/MusicPlayerService;)V", "getService", "Lcom/example/jamendomusicplayer/player/MusicPlayerService;", "app_debug"})
    public final class MusicPlayerBinder extends android.os.Binder {
        
        public MusicPlayerBinder() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.jamendomusicplayer.player.MusicPlayerService getService() {
            return null;
        }
    }
}