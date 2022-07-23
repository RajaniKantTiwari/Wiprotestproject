package com.wipro.db.dao

import androidx.annotation.NonNull
import androidx.annotation.WorkerThread
import androidx.room.*
import com.wipro.db.entity.TvShowEntity

@Dao
interface MovieListDao {
    @WorkerThread
    @Query("SELECT * FROM TvShowEntity LIMIT :pageSize OFFSET :pageIndex")
    suspend fun getMoviesList( pageSize: Int, pageIndex: Int): List<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(moviesList: List<TvShowEntity>)

}