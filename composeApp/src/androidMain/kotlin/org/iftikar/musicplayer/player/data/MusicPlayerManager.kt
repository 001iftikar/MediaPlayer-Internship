package org.iftikar.musicplayer.player.data

import android.media.AudioAttributes
import android.media.MediaPlayer
import org.iftikar.musicplayer.player.domain.AudioPlayer

class MusicPlayerManager(
) : AudioPlayer {
    private var mediaPlayer: MediaPlayer? = null
    override var isPrepared = false
        private set

    override fun play(url: String, onPrepared: (() -> Unit)?) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer().apply {
                setDataSource(url)
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build()
                )
                setOnPreparedListener {
                    isPrepared = true
                    start()
                    onPrepared?.invoke()
                }
                setOnErrorListener { mp, what, extra ->
                    println("Error: $what, Extra: $extra")
                    resetPlayer()
                    true
                }
                prepareAsync()
            }
        } else {
            if (isPrepared) mediaPlayer?.start()
        }
    }

    override fun pause() {
        println("pause invoked")
        mediaPlayer?.pause()
    }

    override fun stop() {
        mediaPlayer?.stop()
    }

    override fun release() {
        mediaPlayer?.release()
        mediaPlayer = null
        isPrepared = false
    }

    override fun getCurrentPosition(): Int = mediaPlayer?.currentPosition ?: 0
    override fun getDuration(): Int = mediaPlayer?.duration ?: 0

    private fun resetPlayer() {
        mediaPlayer?.reset()
        isPrepared = false
    }
}

































