package com.example.jamendomusicplayer.player

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.support.v4.media.session.MediaSessionCompat
import androidx.core.app.NotificationCompat
import androidx.media.app.NotificationCompat.MediaStyle
import androidx.media.session.MediaButtonReceiver
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.jamendomusicplayer.R
import com.example.jamendomusicplayer.data.model.Track
import com.example.jamendomusicplayer.ui.main.MainActivity
import kotlinx.coroutines.*

class MusicPlayerService : Service(), MediaPlayer.OnPreparedListener,
    MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener {

    inner class MusicPlayerBinder : Binder() {
        fun getService(): MusicPlayerService = this@MusicPlayerService
    }

    private val binder = MusicPlayerBinder()

    private var mediaPlayer: MediaPlayer? = null
    private val scope = CoroutineScope(Dispatchers.Main + Job())
    private var progressJob: Job? = null

    var playlist: List<Track> = emptyList()
    private var currentIndex: Int = -1

    var currentTrack: Track? = null
        private set

    var isPlaying: Boolean = false
        private set

    var currentPosition: Int = 0
        private set

    var totalDuration: Int = 0
        private set

    private lateinit var mediaSession: MediaSessionCompat
    private var albumBitmap: Bitmap? = null

    override fun onBind(intent: Intent?): IBinder = binder

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()

        mediaSession = MediaSessionCompat(this, "JamendoPlayer")
        mediaSession.isActive = true
    }

    override fun onDestroy() {
        super.onDestroy()
        stopProgressUpdates()
        mediaSession.release()
        releasePlayer()
    }

    override fun onPrepared(mp: MediaPlayer?) {
        mp?.start()
        isPlaying = true
        totalDuration = mp?.duration ?: 0
        startProgressUpdates()

        startForeground(NOTIFICATION_ID, buildNotification())
    }

    override fun onCompletion(mp: MediaPlayer?) {
        stopProgressUpdates()
        isPlaying = false
        currentPosition = totalDuration

        if (currentIndex + 1 < playlist.size) {
            playAt(currentIndex + 1)
        } else {
            stopForeground(false)
            stopSelf()
        }
    }

    override fun onError(mp: MediaPlayer?, what: Int, extra: Int): Boolean {
        stopProgressUpdates()
        isPlaying = false
        return true
    }

    fun setPlaylist(tracks: List<Track>, startIndex: Int) {
        playlist = tracks
        playAt(startIndex)
    }

    fun playAt(index: Int) {
        if (index < 0 || index >= playlist.size) return
        currentIndex = index
        currentTrack = playlist[index]

        loadAlbumArtAsync(currentTrack?.thumbnailUrl)
        prepareAndPlay(currentTrack?.audioUrl ?: return)
    }

    fun togglePlayPause() {
        val mp = mediaPlayer ?: return
        if (mp.isPlaying) {
            mp.pause()
            isPlaying = false
            stopProgressUpdates()
        } else {
            mp.start()
            isPlaying = true
            startProgressUpdates()
        }
        updateNotification()
    }

    fun seekTo(positionMs: Int) {
        mediaPlayer?.seekTo(positionMs)
    }

    fun playNext() {
        if (playlist.isEmpty()) return
        val nextIndex = if (currentIndex + 1 < playlist.size) currentIndex + 1 else 0
        playAt(nextIndex)
    }

    fun playPrevious() {
        if (playlist.isEmpty()) return
        val prevIndex = if (currentIndex - 1 >= 0) currentIndex - 1 else playlist.lastIndex
        playAt(prevIndex)
    }

    private fun prepareAndPlay(url: String) {
        stopProgressUpdates()
        releasePlayer()
        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setOnPreparedListener(this@MusicPlayerService)
            setOnCompletionListener(this@MusicPlayerService)
            setOnErrorListener(this@MusicPlayerService)
            setDataSource(url)
            prepareAsync()
        }
    }

    private fun releasePlayer() {
        mediaPlayer?.release()
        mediaPlayer = null
    }

    private fun startProgressUpdates() {
        stopProgressUpdates()

        val mp = mediaPlayer ?: return
        progressJob = scope.launch {
            while (isActive && mp.isPlaying) {
                currentPosition = mp.currentPosition
                totalDuration = mp.duration
                updateNotification()
                delay(500L)
            }
        }
    }

    private fun stopProgressUpdates() {
        progressJob?.cancel()
        progressJob = null
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Music Playback",
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    // ------------------------------
    //  LOAD ALBUM ART
    // ------------------------------
    private fun loadAlbumArtAsync(url: String?) {
        scope.launch(Dispatchers.IO) {
            try {
                val loader = ImageLoader(this@MusicPlayerService)
                val request = ImageRequest.Builder(this@MusicPlayerService)
                    .data(url)
                    .allowHardware(false)
                    .build()

                val result = loader.execute(request) as? SuccessResult
                albumBitmap = result?.drawable?.let {
                    (it as android.graphics.drawable.BitmapDrawable).bitmap
                }

                updateNotification()
            } catch (e: Exception) {
                albumBitmap = null
            }
        }
    }

    // ------------------------------
    //  BUILD NOTIFICATION
    // ------------------------------
    private fun buildNotification(): Notification {
        val track = currentTrack
        val title = track?.title ?: "Playing"
        val artist = track?.artist ?: ""

        val openIntent = Intent(this, MainActivity::class.java)
        val openPendingIntent = PendingIntent.getActivity(
            this, 0, openIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val playPauseIcon =
            if (isPlaying) R.drawable.ic_pause_24 else R.drawable.ic_play_arrow_24

        val playPauseIntent = PendingIntent.getService(
            this, 1,
            Intent(this, MusicPlayerService::class.java).apply { action = ACTION_TOGGLE },
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val nextIntent = PendingIntent.getService(
            this, 2,
            Intent(this, MusicPlayerService::class.java).apply { action = ACTION_NEXT },
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val prevIntent = PendingIntent.getService(
            this, 3,
            Intent(this, MusicPlayerService::class.java).apply { action = ACTION_PREV },
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(artist)
            .setSmallIcon(R.drawable.ic_music_note_24)
            .setLargeIcon(albumBitmap)
            .setContentIntent(openPendingIntent)
            .setOngoing(isPlaying)
            .addAction(R.drawable.ic_skip_previous_24, "Previous", prevIntent)
            .addAction(playPauseIcon, "PlayPause", playPauseIntent)
            .addAction(R.drawable.ic_skip_next_24, "Next", nextIntent)
            .setStyle(
                MediaStyle()
                    .setMediaSession(mediaSession.sessionToken)
                    .setShowActionsInCompactView(0, 1, 2)
            )
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .build()
    }

    private fun updateNotification() {
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(NOTIFICATION_ID, buildNotification())
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        when (intent?.action) {
            ACTION_TOGGLE -> togglePlayPause()
            ACTION_NEXT -> playNext()
            ACTION_PREV -> playPrevious()
        }

        return START_STICKY
    }

    companion object {
        private const val CHANNEL_ID = "music_playback_channel"
        private const val NOTIFICATION_ID = 1001

        const val ACTION_TOGGLE = "ACTION_TOGGLE"
        const val ACTION_NEXT = "ACTION_NEXT"
        const val ACTION_PREV = "ACTION_PREV"
    }
}
