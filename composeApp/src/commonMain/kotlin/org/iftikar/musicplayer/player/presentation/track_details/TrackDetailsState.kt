package org.iftikar.musicplayer.player.presentation.track_details

data class TrackDetailsState(
    val isPlaying: Boolean = false,
    val isPrepared: Boolean = false,
    val isLoading: Boolean = false,
    val progress: Float = 0F,
    val totalDuration: Int = 0,
    val progressedDuration: Int = 0
)