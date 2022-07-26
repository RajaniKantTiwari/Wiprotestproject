package com.wipro.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wipro.db.dao.MovieListDao
import com.wipro.db.entity.TvShowEntity

@Database(entities = [TvShowEntity::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun moviesListDao(): MovieListDao
    companion object {
            @Volatile
            private var instance: MovieDatabase? = null

            @JvmStatic
            fun getInstance(context: Context): MovieDatabase =
                instance ?: synchronized(this) {
                    instance ?: buildDatabase(context).also { instance = it }
                }

            private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext, MovieDatabase::class.java, "movies.db").build()
    }
}