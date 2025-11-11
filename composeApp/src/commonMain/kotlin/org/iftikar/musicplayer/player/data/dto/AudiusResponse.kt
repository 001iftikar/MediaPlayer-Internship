package org.iftikar.musicplayer.player.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AudiusResponse(
    val data: List<AudiusTrackDto>
)

@Serializable
data class AudiusTrackDto(
    val id: String,
    val title: String,
    val duration: Int,
    val user: AudiusUserDto,
    val artwork: ArtworkDto? = null
)

@Serializable
data class AudiusUserDto(
    val name: String
)

@Serializable
data class ArtworkDto(
    @SerialName("480x480") val image: String? = null
)
