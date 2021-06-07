package com.dicoding.picodiploma.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.dicoding.picodiploma.moviecatalogue.data.source.local.entity.MovieEntity
import com.dicoding.picodiploma.moviecatalogue.data.source.local.entity.TvShowEntity
import com.dicoding.picodiploma.moviecatalogue.vo.Resource

interface MovieDataSource {

    fun getAllMovie(): LiveData<Resource<List<MovieEntity>>>

    fun getMovieById(id: String): LiveData<Resource<MovieEntity>>

    fun getAllTvShow(): LiveData<Resource<List<TvShowEntity>>>

    fun getTvShowById(id: String): LiveData<Resource<TvShowEntity>>

    fun getMovieFavorite(): LiveData<List<MovieEntity>>

    fun setMovieFavorite(movie: MovieEntity, state: Boolean)

    fun getTvShowFavorite(): LiveData<List<TvShowEntity>>

    fun setTvShowFavorite(tvShow: TvShowEntity, state: Boolean)
}