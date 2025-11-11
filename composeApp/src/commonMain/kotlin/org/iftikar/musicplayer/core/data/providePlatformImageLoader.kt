package org.iftikar.musicplayer.core.data

import coil3.ImageLoader
import coil3.PlatformContext
import io.ktor.client.HttpClient

expect fun providePlatformImageLoader(
    context: PlatformContext,
    httpClient: HttpClient
): ImageLoader