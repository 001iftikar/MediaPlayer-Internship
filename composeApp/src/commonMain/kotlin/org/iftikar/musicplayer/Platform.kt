package org.iftikar.musicplayer

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform