package org.iftikar.musicplayer.player.presentation.track_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil3.ImageLoader
import coil3.compose.AsyncImage
import mediaplayer_internship.composeapp.generated.resources.Res
import mediaplayer_internship.composeapp.generated.resources.music_placeholder
import org.iftikar.musicplayer.app.Route
import org.iftikar.musicplayer.player.domain.model.Track
import org.iftikar.musicplayer.utils.toReadableTime
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackListScreen(
    trackListViewModel: TrackListViewModel,
    navHostController: NavHostController
) {
    val state by trackListViewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text("Songs")
                }
            )
        }
    ) { innerPadding ->
        if (state.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize().padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                LinearProgressIndicator()
            }
        }

        if (state.error != null) {
            Box(
                modifier = Modifier.fillMaxSize().padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = state.error!!,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxWidth().padding(innerPadding)
        ) {
            items(items = state.trackList, key = { it.id }) { track ->
                TrackItem(
                    track = track,
                    onClick = {
                        navHostController.navigate(Route.TrackDetailsScreen(
                            id = track.id,
                            name = track.title,
                            duration = track.duration,
                            artistName = track.artist,
                            audio = track.audio,
                            image = track.image
                        ))
                    }
                )
            }
        }
    }
}

@Composable
private fun TrackItem(track: Track, onClick: () -> Unit) {
    val imageLoader: ImageLoader = koinInject()
    Row(
        modifier = Modifier.fillMaxWidth()
            .clickable(
                onClick = onClick
            ),
        horizontalArrangement = Arrangement.Start
    ) {
        AsyncImage(
            model = track.image,
            imageLoader = imageLoader,
            contentDescription = track.title,
            modifier = Modifier
                .size(72.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(Res.drawable.music_placeholder),
            error = painterResource(Res.drawable.music_placeholder)
        )

        Spacer(Modifier.width(12.dp))

        Column {
            Text(
                text = track.title,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = track.artist,
                style = MaterialTheme.typography.titleSmall
            )

            Text(
                text = track.duration.toReadableTime(),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}


































