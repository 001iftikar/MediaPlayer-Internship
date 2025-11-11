package org.iftikar.musicplayer.player.presentation.track_list

import org.iftikar.musicplayer.player.domain.model.Track

data class TrackListState(
    val trackList: List<Track> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val sortType: SortType = SortType.NAME
)
