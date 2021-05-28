package com.dicoding.picodiploma.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.moviecatalogue.data.source.MovieRepository
import com.dicoding.picodiploma.moviecatalogue.data.source.local.entity.MovieEntity
import com.dicoding.picodiploma.moviecatalogue.data.source.local.entity.TvShowEntity

class DetailViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    private lateinit var movieId: String
    private lateinit var tvShowId: String

    fun setSelectedMovie(movieId: String) {
        this.movieId = movieId
    }

    fun setSelectedTvs(tvShowId: String) {
        this.tvShowId = tvShowId
    }

    fun getMovie(): LiveData<MovieEntity> = movieRepository.getMovieById(movieId)

    fun getTvs(): LiveData<TvShowEntity> = movieRepository.getTvShowById(tvShowId)
}