package org.iftikar.musicplayer.core.data

import coil3.ImageLoader
import coil3.PlatformContext
import coil3.network.ktor3.KtorNetworkFetcherFactory
import io.ktor.client.HttpClient
actual fun providePlatformImageLoader(
    context: PlatformContext,
    httpClient: HttpClient
): ImageLoader = ImageLoader.Builder(context)
    .components { add(KtorNetworkFetcherFactory(httpClient)) }
    .build()