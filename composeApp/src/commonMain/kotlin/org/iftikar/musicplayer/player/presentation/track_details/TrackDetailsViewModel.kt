package org.iftikar.musicplayer.player.presentation.track_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.iftikar.musicplayer.player.domain.AudioPlayer

class TrackDetailsViewModel(private val audioPlayer: AudioPlayer) : ViewModel() {
    private val _state = MutableStateFlow(TrackDetailsState())
    val state = _state.asStateFlow()

    fun play(url: String) {
        _state.update { it.copy(isLoading = true) }
        audioPlayer.play(url) {
            print("Play invoked")
            _state.update { it.copy(isPrepared = true, isPlaying = true) }

            viewModelScope.launch {
                while (_state.value.isPlaying) {
                    val duration = audioPlayer.getDuration()
                    val position = audioPlayer.getCurrentPosition()
                    if (duration > 0) {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                progress = position.toFloat() / duration.toFloat(),
                                totalDuration = duration,
                                progressedDuration = position
                            )
                        }
                    }
                    delay(500)
                }
            }
        }
    }

    fun pause() {
        println("Pause invoked")
        audioPlayer.pause()
        _state.update { it.copy(isPrepared = audioPlayer.isPrepared, isPlaying = false) }
    }


    override fun onCleared() {
        super.onCleared()
        audioPlayer.release()
    }
}

















































