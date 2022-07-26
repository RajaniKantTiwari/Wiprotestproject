package com.wipro

import android.app.Application
import com.wipro.networking.*
import com.wipro.repository.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MoviesApplication :Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MoviesApplication)
            modules(listOf(
                networkModule, viewModelModule, repositoryModule,
                movieDataSourceFactory, appDataBase
            ))
        }
    }
}