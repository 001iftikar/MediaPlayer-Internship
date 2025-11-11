package org.iftikar.musicplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.iftikar.musicplayer.app.App
import org.iftikar.musicplayer.player.presentation.track_details.TrackDetailsViewModel
import org.iftikar.musicplayer.player.presentation.track_list.TrackListViewModel
import org.koin.android.ext.android.getKoin

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        val trackListViewModel: TrackListViewModel = getKoin().get()
        val trackDetailsViewModel: TrackDetailsViewModel = getKoin().get()
        setContent {
            App(trackListViewModel, trackDetailsViewModel)
        }
    }
}