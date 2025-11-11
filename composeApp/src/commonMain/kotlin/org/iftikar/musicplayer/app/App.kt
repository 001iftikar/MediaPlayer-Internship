package org.iftikar.musicplayer.app

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.navigation.toRoute
import org.iftikar.musicplayer.player.domain.model.Track
import org.iftikar.musicplayer.player.presentation.track_details.TrackDetailsScreen
import org.iftikar.musicplayer.player.presentation.track_details.TrackDetailsViewModel
import org.iftikar.musicplayer.player.presentation.track_list.TrackListScreen
import org.iftikar.musicplayer.player.presentation.track_list.TrackListViewModel

@Composable
fun App(trackListViewModel: TrackListViewModel, trackDetailsViewModel: TrackDetailsViewModel) {
    val navHostController = rememberNavController()
    MaterialTheme {
        NavHost(
            navController = navHostController, startDestination = Route.NavGraph
        ) {
            navigation<Route.NavGraph>(startDestination = Route.TrackListScreenRoute)
            {
                composable<Route.TrackListScreenRoute>(
                    exitTransition = { slideOutHorizontally() },
                    popEnterTransition = { slideInHorizontally() }
                ) {
                    TrackListScreen(trackListViewModel, navHostController)
                }

                composable<Route.TrackDetailsScreen>(
                    enterTransition = {
                        slideInHorizontally { initialOffset ->
                            initialOffset
                        }
                    },
                    exitTransition = {
                        slideOutHorizontally { initialOffset ->
                            initialOffset
                        }
                    }
                ) { backStack ->
                    val args = backStack.toRoute<Route.TrackDetailsScreen>()
                    TrackDetailsScreen(
                        viewModel = trackDetailsViewModel,
                        track = Track(
                            id = args.id,
                            title = args.name,
                            duration = args.duration,
                            artist = args.artistName,
                            audio = args.audio,
                            image = args.image
                        )
                    )
                }
            }
        }
    }
}