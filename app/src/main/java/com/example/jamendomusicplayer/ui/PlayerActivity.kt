package com.example.jamendomusicplayer.ui

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.jamendomusicplayer.R
import com.example.jamendomusicplayer.databinding.ActivityPlayerBinding
import com.example.jamendomusicplayer.player.MusicPlayerService
import com.example.jamendomusicplayer.ui.main.MusicAdapter
import com.example.jamendomusicplayer.utils.ThemeUtils
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.max
import kotlin.math.min

class PlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayerBinding

    private var musicService: MusicPlayerService? = null
    private var isBound = false
    private var progressJob: Job? = null
    private lateinit var adapter: MusicAdapter

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as MusicPlayerService.MusicPlayerBinder
            musicService = binder.getService()
            isBound = true
            syncUiFromService()
            startProgressUpdates()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
            musicService = null
            stopProgressUpdates()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // Apply theme
        ThemeUtils.applySavedTheme(this)

        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // match toolbar & status bar color
        window.statusBarColor = getColor(R.color.backgroundMain)

        // back button handling
        binding.playerToolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        setupTrackList()


        // Fill initial UI from intent (so it looks instant)
        val title = intent.getStringExtra(EXTRA_TITLE) ?: ""
        val artist = intent.getStringExtra(EXTRA_ARTIST) ?: ""
        val imageUrl = intent.getStringExtra(EXTRA_IMAGE)

        binding.textSongTitle.text = title
        binding.textArtist.text = artist

        imageUrl?.let {
            binding.imageCoverArt.load(it) {
                crossfade(true)
                placeholder(R.drawable.ic_music_note_24)
                error(R.drawable.ic_music_note_24)
            }
            binding.imageBlurBackground.load(it) {
                crossfade(true)
                placeholder(R.drawable.ic_music_note_24)
                error(R.drawable.ic_music_note_24)
            }
        }

        setupControls()
    }

    override fun onStart() {
        super.onStart()
        Intent(this, MusicPlayerService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        if (isBound) {
            unbindService(connection)
            isBound = false
        }
        stopProgressUpdates()
    }

    private fun setupControls() {
        binding.buttonPlayPause.setOnClickListener {
            musicService?.togglePlayPause()
            syncUiFromService()
        }

        binding.buttonNext.setOnClickListener {
            musicService?.playNext()
            syncUiFromService()
        }

        binding.buttonPrev.setOnClickListener {
            musicService?.playPrevious()
            syncUiFromService()
        }

        binding.buttonBack10.setOnClickListener {
            val service = musicService ?: return@setOnClickListener
            val newPos = max(service.currentPosition - 10_000, 0)
            service.seekTo(newPos)
            syncUiFromService()
        }

        binding.buttonForward10.setOnClickListener {
            val service = musicService ?: return@setOnClickListener
            val newPos = min(service.currentPosition + 10_000, service.totalDuration)
            service.seekTo(newPos)
            syncUiFromService()
        }

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    musicService?.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun setupTrackList() {
        adapter = MusicAdapter { track, index ->
            // Play selected track
            musicService?.playAt(index)
            syncUiFromService()
        }

        binding.recyclerPlayerTracks.layoutManager =
            LinearLayoutManager(this)

        binding.recyclerPlayerTracks.adapter = adapter

        // Poll until service is connected then update list
        lifecycleScope.launch {
            while (musicService == null) delay(100)
            updateTrackList()
        }
    }

    private fun updateTrackList() {
        val service = musicService ?: return
        val tracks = service.playlist
        adapter.submitList(tracks)
    }



    private fun startProgressUpdates() {
        stopProgressUpdates()
        progressJob = lifecycleScope.launch {
            while (true) {
                syncUiFromService()
                delay(500L)
            }
        }
    }

    private fun stopProgressUpdates() {
        progressJob?.cancel()
        progressJob = null
    }

//    private fun syncUiFromService() {
//        val service = musicService ?: return
//        val track = service.currentTrack
//
//        if (track != null) {
//            binding.textSongTitle.text = track.title
//            binding.textArtist.text = track.artist
//
//            track.thumbnailUrl?.let {
//                binding.imageCoverArt.load(it) {
//                    crossfade(true)
//                    placeholder(R.drawable.ic_music_note_24)
//                    error(R.drawable.ic_music_note_24)
//                }
//                binding.imageBlurBackground.load(it) {
//                    crossfade(true)
//                    placeholder(R.drawable.ic_music_note_24)
//                    error(R.drawable.ic_music_note_24)
//                }
//            }
//        }
//
//        val duration = service.totalDuration
//        val position = service.currentPosition
//
//        binding.seekBar.max = duration.coerceAtLeast(0)
//        binding.seekBar.progress = position.coerceAtLeast(0)
//
//        binding.textCurrentTime.text = formatMs(position)
//        binding.textTotalTime.text = formatMs(duration)
//
//        val icon =
//            if (service.isPlaying) R.drawable.ic_pause_24
//            else R.drawable.ic_play_arrow_24
//
//        binding.buttonPlayPause.setImageResource(icon)
//    }

    private fun syncUiFromService() {
        val service = musicService ?: return
        val track = service.currentTrack

        if (track != null) {
            binding.textSongTitle.text = track.title
            binding.textArtist.text = track.artist

            track.thumbnailUrl?.let {
                binding.imageCoverArt.load(it) {
                    crossfade(true)
                    placeholder(R.drawable.ic_music_note_24)
                    error(R.drawable.ic_music_note_24)
                }
                binding.imageBlurBackground.load(it) {
                    crossfade(true)
                    placeholder(R.drawable.ic_music_note_24)
                    error(R.drawable.ic_music_note_24)
                }
            }
        }

        // Update list (highlight current automatically)
        updateTrackList()

        val duration = service.totalDuration
        val position = service.currentPosition

        binding.seekBar.max = duration.coerceAtLeast(0)
        binding.seekBar.progress = position.coerceAtLeast(0)

        binding.textCurrentTime.text = formatMs(position)
        binding.textTotalTime.text = formatMs(duration)

        binding.buttonPlayPause.setImageResource(
            if (service.isPlaying) R.drawable.ic_pause_24
            else R.drawable.ic_play_arrow_24
        )
    }

    private fun formatMs(ms: Int): String {
        val totalSeconds = ms / 1000
        val minutes = totalSeconds / 60
        val seconds = totalSeconds % 60
        return String.format("%d:%02d", minutes, seconds)
    }

    companion object {
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_ARTIST = "extra_artist"
        const val EXTRA_IMAGE = "extra_image"
    }
}
