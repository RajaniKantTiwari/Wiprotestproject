package com.wipro.repository

import com.wipro.networking.*
import com.wipro.viewmodel.MoviesListViewModel
import org.koin.dsl.module

val viewModelModule = module {
  factory {
    MoviesListViewModel(get())
  }
}

val appDataBase = module {

}

val repositoryModule = module {
    single<MoviesRepository>(createdAtStart = true) {
       MoviesRepositoryImpl(get(),get())
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