package org.iftikar.musicplayer.player.data.mappers

import org.iftikar.musicplayer.player.data.dto.AudiusTrackDto
import org.iftikar.musicplayer.player.domain.model.Track

fun AudiusTrackDto.toTrack(): Track {
    return Track(
        id = id,
        title = title,
        duration = duration,
        artist = user.name,
        audio = "https://discoveryprovider.audius.co/v1/tracks/$id/stream",
        image = artwork?.image
    )
}