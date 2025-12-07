package com.example.jamendomusicplayer.ui.main

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jamendomusicplayer.R
import com.example.jamendomusicplayer.data.model.Track
import com.example.jamendomusicplayer.databinding.ActivityMainBinding
import com.example.jamendomusicplayer.player.MusicPlayerService
import com.example.jamendomusicplayer.ui.PlayerActivity
import com.example.jamendomusicplayer.utils.ThemeUtils
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MusicViewModel by viewModels {
        MusicViewModelFactory(this)
    }

    private lateinit var adapter: MusicAdapter

    private var musicService: MusicPlayerService? = null
    private var isBound = false
    private var seekJob: Job? = null
    private var pendingStartIndex: Int? = null

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as MusicPlayerService.MusicPlayerBinder
            musicService = binder.getService()
            isBound = true
            startSeekUpdates()

            // If a track was clicked before binding, play it now
            pendingStartIndex?.let { index ->
                val tracks = viewModel.uiState.value.tracks
                if (tracks.isNotEmpty()) {
                    musicService?.setPlaylist(tracks, index)
                }
                pendingStartIndex = null
            }

            updatePlaybackStateFromService()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
            musicService = null
            stopSeekUpdates()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // Apply theme before view inflation
        ThemeUtils.applySavedTheme(this)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        window.statusBarColor = getColor(R.color.colorPrimary)

        setupThemeToggle()
        setupRecycler()
        setupSortControls()
        setupPlaybackControls()

        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                renderState(state)
            }
        }

        viewModel.loadTracks()
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
        stopSeekUpdates()
    }

    private fun setupThemeToggle() {
        binding.buttonToggleTheme.setOnClickListener {
            ThemeUtils.toggleTheme(this)
        }
    }

    private fun setupRecycler() {
        adapter = MusicAdapter { track, index ->
            viewModel.onTrackSelected(track)
            startMusicService(index)
            openPlayerActivity(track)
        }

        binding.recyclerTracks.layoutManager = LinearLayoutManager(this)
        binding.recyclerTracks.adapter = adapter
    }

    private fun setupSortControls() {
        binding.chipSortName.setOnClickListener {
            viewModel.changeSortMode(SortMode.NAME_ASC)
        }
        binding.chipSortDuration.setOnClickListener {
            viewModel.changeSortMode(SortMode.DURATION_ASC)
        }
    }

    private fun setupPlaybackControls() {
        binding.buttonPlayPause.setOnClickListener {
            if (musicService == null) {
                val currentTrack = viewModel.uiState.value.currentlyPlaying
                val index = viewModel.uiState.value.tracks.indexOf(currentTrack)
                if (index >= 0) {
                    startMusicService(index)
                } else {
                    Toast.makeText(this, "Select a track to play", Toast.LENGTH_SHORT).show()
                }
            } else {
                musicService?.togglePlayPause()
                updatePlaybackStateFromService()
            }
        }

        binding.buttonNext.setOnClickListener {
            musicService?.playNext()
            updatePlaybackStateFromService()
        }

        binding.buttonPrevious.setOnClickListener {
            musicService?.playPrevious()
            updatePlaybackStateFromService()
        }

        binding.seekBarProgress.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) musicService?.seekTo(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // Tap mini-player to open full screen player
        binding.nowPlayingContainer.setOnClickListener {
            val track = viewModel.uiState.value.currentlyPlaying
            if (track != null) openPlayerActivity(track)
        }
    }

    private fun startMusicService(index: Int) {
        val tracks = viewModel.uiState.value.tracks
        if (tracks.isEmpty()) return

        val serviceIntent = Intent(this, MusicPlayerService::class.java)
        startService(serviceIntent)

        if (isBound && musicService != null) {
            musicService?.setPlaylist(tracks, index)
            updatePlaybackStateFromService()
            startSeekUpdates()
        } else {
            pendingStartIndex = index
            bindService(serviceIntent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    private fun openPlayerActivity(track: Track) {
        val intent = Intent(this, PlayerActivity::class.java).apply {
            putExtra(PlayerActivity.EXTRA_TITLE, track.title)
            putExtra(PlayerActivity.EXTRA_ARTIST, track.artist)
            putExtra(PlayerActivity.EXTRA_IMAGE, track.thumbnailUrl)
        }
        startActivity(intent)
    }

    private fun renderState(state: MusicUiState) {
        binding.progressBar.isVisible = state.isLoading
        binding.textError.apply {
            isVisible = state.errorMessage != null
            text = state.errorMessage ?: ""
        }

        binding.textOffline.isVisible = state.isFromCache

        adapter.submitList(state.tracks)

        val track = state.currentlyPlaying
        if (track == null) {
            binding.nowPlayingContainer.visibility = View.GONE
        } else {
            binding.nowPlayingContainer.visibility = View.VISIBLE
            binding.textNowPlayingTitle.text = track.title
            binding.textNowPlayingArtist.text = track.artist
        }

//        binding.buttonPlayPause.text =
//            if (state.isPlaying) getString(R.string.action_pause)
//            else getString(R.string.action_play)

        binding.buttonPlayPause.setImageResource(
            if (state.isPlaying) R.drawable.ic_pause_24
            else R.drawable.ic_play_arrow_24
        )

        binding.seekBarProgress.max = state.totalDurationMs
        binding.seekBarProgress.progress = state.currentPositionMs
    }

    private fun startSeekUpdates() {
        stopSeekUpdates()
        seekJob = lifecycleScope.launch {
            while (true) {
                updatePlaybackStateFromService()
                delay(500L)
            }
        }
    }

    private fun stopSeekUpdates() {
        seekJob?.cancel()
        seekJob = null
    }

    private fun updatePlaybackStateFromService() {
        val s = musicService ?: return
        val track = s.currentTrack
        if (track != null) viewModel.onTrackSelected(track)

        viewModel.updatePlaybackState(
            isPlaying = s.isPlaying,
            currentPos = s.currentPosition,
            totalDuration = s.totalDuration
        )
    }
}
