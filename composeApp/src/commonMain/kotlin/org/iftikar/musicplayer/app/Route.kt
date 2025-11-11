package org.iftikar.musicplayer.app

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object NavGraph : Route
    @Serializable
    data object TrackListScreenRoute : Route
    @Serializable
    data class TrackDetailsScreen(
        val id: String,
        val name: String,
        val duration: Int,
        val artistName: String,
        val audio: String,
        val image: String? = null
    ) : Route
}