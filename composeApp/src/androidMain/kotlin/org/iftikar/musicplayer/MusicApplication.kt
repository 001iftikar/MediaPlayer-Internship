package org.iftikar.musicplayer

import android.app.Application
import org.iftikar.musicplayer.di.initKoin
import org.iftikar.musicplayer.di.sharedModule
import org.koin.android.ext.koin.androidContext

class MusicApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MusicApplication)
        }
    }
}