package com.dicoding.picodiploma.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import com.dicoding.picodiploma.moviecatalogue.data.source.local.entity.MovieEntity
import com.dicoding.picodiploma.moviecatalogue.data.source.local.entity.TvShowEntity
import com.dicoding.picodiploma.moviecatalogue.data.source.local.room.MovieDao

class LocalDataSource private constructor(private val mMovieDao: MovieDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource =
            INSTANCE ?: getInstance(movieDao)
    }

    fun getAllMovies(): LiveData<List<MovieEntity>> = mMovieDao.getMovies()

    fun getAllTvShows(): LiveData<List<TvShowEntity>> = mMovieDao.getTvShows()

    fun getMoviesWithId(id: String): LiveData<MovieEntity> =
        mMovieDao.getMovieWithId(id)

    fun getTvShowWithId(id: String): LiveData<TvShowEntity> =
        mMovieDao.getTvShowWithId(id)

    fun getFavoritedMovie(): LiveData<List<MovieEntity>> = mMovieDao.getMovieFavorited()

    fun getFavoritedTvShow(): LiveData<List<TvShowEntity>> = mMovieDao.getTvShowFavorited()

    fun insertMovies(movies: List<MovieEntity>) = mMovieDao.insertMovies(movies)

    fun insertTvShow(tvShow: List<TvShowEntity>) = mMovieDao.insertTvShow(tvShow)

    fun setMovieFavorite(movie: MovieEntity, newState: Boolean) {
        movie.favorited = newState
        mMovieDao.updateMovies(movie)
    }

    fun setTvShowFavorite(tvshow: TvShowEntity, newState: Boolean) {
        tvshow.favorited = newState
        mMovieDao.updateTvshow(tvshow)
    }
}
