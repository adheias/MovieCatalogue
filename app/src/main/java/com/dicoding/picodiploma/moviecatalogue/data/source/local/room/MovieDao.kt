package com.dicoding.picodiploma.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dicoding.picodiploma.moviecatalogue.data.source.local.entity.MovieEntity
import com.dicoding.picodiploma.moviecatalogue.data.source.local.entity.TvShowEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movieentities")
    fun getMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM tvshowentities")
    fun getTvShows(): LiveData<List<TvShowEntity>>

    @Query("SELECT * FROM movieentities where favorited = 1")
    fun getMovieFavorited(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM tvshowentities where favorited = 1")
    fun getTvShowFavorited(): LiveData<List<TvShowEntity>>

    @Transaction
    @Query("SELECT * FROM movieentities WHERE id = :id")
    fun getMovieWithId(id: String): LiveData<MovieEntity>

    @Query("SELECT * FROM tvshowentities WHERE id = :id")
    fun getTvShowWithId(id: String): LiveData<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(tvShow: List<TvShowEntity>)

    @Update
    fun updateMovies(movie: MovieEntity)

    @Update
    fun updateTvshow(tvShow: TvShowEntity)

}