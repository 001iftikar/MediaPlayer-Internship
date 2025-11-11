package org.iftikar.musicplayer.di

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.iftikar.musicplayer.player.data.MusicPlayerManager
import org.iftikar.musicplayer.player.domain.AudioPlayer
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single<HttpClientEngine> { OkHttp.create() }
        singleOf(::MusicPlayerManager).bind<AudioPlayer>()
    }