package com.wipro

import android.app.Application
import com.wipro.repository.movieDataSourceFactory
import com.wipro.repository.networkModule
import com.wipro.repository.repositoryModule
import com.wipro.repository.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MoviesApplication :Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MoviesApplication)
            modules(listOf(networkModule, viewModelModule, repositoryModule, movieDataSourceFactory))
        }
    }
}