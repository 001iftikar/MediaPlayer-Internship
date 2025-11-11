package org.iftikar.musicplayer.player.domain.repository

import org.iftikar.musicplayer.core.domain.DataError
import org.iftikar.musicplayer.core.domain.Result
import org.iftikar.musicplayer.player.domain.model.Track

interface TrackRepository {
    suspend fun fetchTracks(): Result<List<Track>, DataError>
}