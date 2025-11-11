package org.iftikar.musicplayer.utils

fun Int.toReadableTime(fromMilliseconds: Boolean = false): String {
    val totalSeconds = if (fromMilliseconds) this / 1000 else this
    val hours = totalSeconds / 3600
    val minutes = (totalSeconds % 3600) / 60
    val seconds = totalSeconds % 60

    return if (hours > 0)
        "${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}"
    else
        "${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}"
}
