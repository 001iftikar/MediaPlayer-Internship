package org.iftikar.musicplayer.player.presentation.track_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.iftikar.musicplayer.core.domain.DataError
import org.iftikar.musicplayer.core.domain.onError
import org.iftikar.musicplayer.core.domain.onSuccess
import org.iftikar.musicplayer.player.domain.model.Track
import org.iftikar.musicplayer.player.domain.repository.TrackRepository

class TrackListViewModel(private val trackRepository: TrackRepository) : ViewModel() {
    private val _state = MutableStateFlow(TrackListState())
    val state = _state.asStateFlow()

    init {
        println("VM initiated")
        fetchTracks()
    }

    private fun fetchTracks() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            trackRepository.fetchTracks().onSuccess { tracks ->
                _state.update { it.copy(trackList = sortTracks(tracks, it.sortType), isLoading = false) }
            }.onError { error ->
                when (error) {
                    DataError.Remote.REQUEST_TIMEOUT -> setError("Request timeout, Please try again")
                    DataError.Remote.TOO_MANY_REQUESTS -> setError("Too many requests, Please try again")
                    DataError.Remote.NO_INTERNET -> setError("No internet connection.")
                    else -> setError("Something went wrong.")
                }
            }
        }
    }

    fun changeSortType(sortType: SortType) {
        viewModelScope.launch(Dispatchers.Default) {
            _state.update { current ->
                current.copy(
                    sortType = sortType,
                    trackList = sortTracks(current.trackList, sortType)
                )
            }
        }
    }

    private fun sortTracks(tracks: List<Track>, sortType: SortType): List<Track> {
        return when (sortType) {
            SortType.NAME -> tracks.sortedBy { it.title.lowercase() }
            SortType.DURATION -> tracks.sortedBy { it.duration }
        }
    }

    private fun setError(error: String) {
        _state.update {
            it.copy(isLoading = false, error = error)
        }
    }
}








































