package com.dicoding.picodiploma.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.dicoding.picodiploma.moviecatalogue.data.source.local.entity.MovieEntity
import com.dicoding.picodiploma.moviecatalogue.data.source.local.entity.TvShowEntity

interface MovieDataSource {

    fun getAllMovie(): LiveData<List<MovieEntity>>

    fun getMovieById(id: String): LiveData<MovieEntity>

    fun getAllTvShow(): LiveData<List<TvShowEntity>>

    fun getTvShowById(id: String): LiveData<TvShowEntity>
}