package com.example.jamendomusicplayer.ui.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.jamendomusicplayer.data.local.AppDatabase
import com.example.jamendomusicplayer.data.remote.JamendoApiServiceImpl
import com.example.jamendomusicplayer.data.repository.MusicRepository
import com.example.jamendomusicplayer.domain.usecase.GetTracksUseCase
import com.example.jamendomusicplayer.domain.usecase.SortTracksUseCase

class MusicViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MusicViewModel::class.java)) {
            val db = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "jamendo_music_db"
            ).build()
            val api = JamendoApiServiceImpl()
            val repository = MusicRepository(api, db.trackDao())
            val getTracksUseCase = GetTracksUseCase(repository)
            val sortTracksUseCase = SortTracksUseCase()
            @Suppress("UNCHECKED_CAST")
            return MusicViewModel(repository, getTracksUseCase, sortTracksUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
