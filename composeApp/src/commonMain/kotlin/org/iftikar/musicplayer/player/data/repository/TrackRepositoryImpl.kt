package org.iftikar.musicplayer.player.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.io.IOException
import kotlinx.serialization.SerializationException
import org.iftikar.musicplayer.core.domain.DataError
import org.iftikar.musicplayer.core.domain.Result
import org.iftikar.musicplayer.player.data.dto.AudiusResponse
import org.iftikar.musicplayer.player.data.mappers.toTrack
import org.iftikar.musicplayer.player.domain.model.Track
import org.iftikar.musicplayer.player.domain.repository.TrackRepository

class TrackRepositoryImpl(private val httpClient: HttpClient) : TrackRepository {
    override suspend fun fetchTracks(): Result<List<Track>, DataError> =
        withContext(Dispatchers.IO) {
            try {
                val response: HttpResponse = httpClient.get(
                    "https://discoveryprovider.audius.co/v1/tracks/trending" // only get the trendings
                )
                val tracks = response.body<AudiusResponse>().data
                println(tracks)
                Result.Success(tracks.map { it.toTrack() })
            } catch (e: HttpRequestTimeoutException) {
                println(e.message)
                Result.Error(DataError.Remote.REQUEST_TIMEOUT)
            } catch (e: IOException) {
                println(e.message)
                Result.Error(DataError.Remote.NO_INTERNET)
            } catch (e: SerializationException) {
                println(e.message)
                Result.Error(DataError.Remote.SERIALIZATION)
            } catch (e: Exception) {
                println(e.message)
                Result.Error(DataError.Remote.UNKNOWN)
            }
        }
}