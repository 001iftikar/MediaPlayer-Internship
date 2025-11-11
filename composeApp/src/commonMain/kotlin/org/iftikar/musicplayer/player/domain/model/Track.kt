package org.iftikar.musicplayer.player.domain.model

data class Track(
    val id: String,
    val title: String,
    val duration: Int,
    val artist: String,
    val audio: String,
    val image: String? = null
)