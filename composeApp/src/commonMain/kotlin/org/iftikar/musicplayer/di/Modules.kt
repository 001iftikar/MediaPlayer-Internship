package org.iftikar.musicplayer.di

import org.iftikar.musicplayer.core.data.HttpClientFactory
import org.iftikar.musicplayer.core.data.providePlatformImageLoader
import org.iftikar.musicplayer.player.data.repository.TrackRepositoryImpl
import org.iftikar.musicplayer.player.domain.repository.TrackRepository
import org.iftikar.musicplayer.player.presentation.track_details.TrackDetailsViewModel
import org.iftikar.musicplayer.player.presentation.track_list.TrackListViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module
val sharedModule = module {
    single { HttpClientFactory.create(get()) }
    singleOf(::TrackRepositoryImpl).bind<TrackRepository>()
    factory { TrackListViewModel(get()) }
    factory { TrackDetailsViewModel(get()) }

    single { providePlatformImageLoader(get(), get()) }
}