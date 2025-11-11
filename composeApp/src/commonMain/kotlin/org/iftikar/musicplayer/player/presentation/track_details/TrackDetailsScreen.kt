package org.iftikar.musicplayer.player.presentation.track_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import mediaplayer_internship.composeapp.generated.resources.Res
import mediaplayer_internship.composeapp.generated.resources.music_placeholder
import org.iftikar.musicplayer.player.domain.model.Track
import org.iftikar.musicplayer.utils.toReadableTime
import org.jetbrains.compose.resources.painterResource

@Composable
fun TrackDetailsScreen(
    viewModel: TrackDetailsViewModel,
    track: Track
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    Scaffold { innerPadding ->
        Track(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            track = track,
            isPlaying = state.isPlaying,
            progress = state.progress,
            totalDuration = state.totalDuration.toReadableTime(true),
            progressedDuration = state.progressedDuration.toReadableTime(true),
            isLoading = state.isLoading,
            onPlay = {
                viewModel.play(track.audio)
            },
            onPause = {
                viewModel.pause()
            }
        )
    }
}

@Composable
private fun Track(
    modifier: Modifier = Modifier,
    isPlaying: Boolean,
    progress: Float,
    totalDuration: String,
    progressedDuration: String,
    isLoading: Boolean,
    track: Track,
    onPlay: () -> Unit,
    onPause: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.size(400.dp)
        ) {
            AsyncImage(
                model = track.image,
                contentDescription = track.title,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(Res.drawable.music_placeholder),
                error = painterResource(Res.drawable.music_placeholder)
            )

        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = track.title,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = track.artist,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )

        if(isLoading) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp))
            )
        } else {
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp))
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(0.8f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = progressedDuration
            )
            Text(
                text = totalDuration
            )
        }

        Button(
            onClick = {
                if (isPlaying) {
                    onPause()
                } else {
                    onPlay()
                }
            }
        ) {
            if (isPlaying) {
                Text("Pause")
            } else {
                Text("Play")
            }
        }
    }
}
































