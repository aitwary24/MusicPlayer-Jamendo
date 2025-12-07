package com.example.jamendomusicplayer.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.jamendomusicplayer.R
import com.example.jamendomusicplayer.data.model.Track

class MusicAdapter(
    private val onTrackClick: (Track, Int) -> Unit
) : ListAdapter<Track, MusicAdapter.TrackViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_track, parent, false)
        return TrackViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TrackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val thumbnail: ImageView = itemView.findViewById(R.id.imageThumbnail)
        private val titleText: TextView = itemView.findViewById(R.id.textTitle)
        private val artistText: TextView = itemView.findViewById(R.id.textArtist)
        private val durationText: TextView = itemView.findViewById(R.id.textDuration)

        fun bind(track: Track) {
            titleText.text = track.title
            artistText.text = track.artist
            val minutes = track.durationSeconds / 60
            val seconds = track.durationSeconds % 60
            durationText.text = String.format("%d:%02d", minutes, seconds)

            thumbnail.load(track.thumbnailUrl) {
                crossfade(true)
                placeholder(R.drawable.ic_music_note_24)
                error(R.drawable.ic_music_note_24)
            }

            itemView.setOnClickListener {
                onTrackClick(track, bindingAdapterPosition)
            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Track>() {
            override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean =
                oldItem == newItem
        }
    }
}
