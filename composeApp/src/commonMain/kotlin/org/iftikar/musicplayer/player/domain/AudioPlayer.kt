package org.iftikar.musicplayer.player.domain

interface AudioPlayer {
    val isPrepared: Boolean // so that I can set isPrepared in state using this
    fun play(url: String, onPrepared: (() -> Unit)? = null) // callback just to inform VM about isPrepared
    fun pause()
    fun stop()
    fun release()
    fun getCurrentPosition(): Int
    fun getDuration(): Int
}