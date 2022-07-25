package com.wipro.networking

import com.wipro.db.MovieDatabase
import com.wipro.repository.*
import com.wipro.viewmodel.MovieDetailsViewModel
import com.wipro.viewmodel.MoviesListViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory {
        MoviesListViewModel(get())
    }
    factory {
        MovieDetailsViewModel(get())
    }
}

val appDataBase = module {
    single { MovieDatabase.getInstance(get()) }
    single { get<MovieDatabase>().moviesListDao() }
}

val repositoryModule = module {
    single<MoviesRepository>(createdAtStart = true) {
        MoviesRepositoryImpl(get(), get())
    }
    single<MovieDetailsRepository>(createdAtStart = true) {
        MovieDetailsRepositoryImpl(get(), get())
    }
}

val networkModule = module {
    factory { provideOkHttpClient(get(), get()) }
    factory { AuthInterceptor() }
    factory { provideLoggingInterceptor() }
    factory { provideMoviesApi(get()) }
    single { provideRetrofit(get()) }
    factory { ResponseHandler() }
}

val movieDataSourceFactory = module {
    factory { MoviesDataSourceFactory(get()) }
    factory { MovieDataSource(get(), get(), get()) }
}