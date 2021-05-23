package com.dicoding.picodiploma.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.moviecatalogue.data.MovieEntity
import com.dicoding.picodiploma.moviecatalogue.data.TvShowEntity
import com.dicoding.picodiploma.moviecatalogue.data.source.MovieRepository

class DetailViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    private lateinit var movie: String
    private lateinit var tvs: String

    fun setSelectedMovie(movie: String) {
        this.movie = movie
    }

    fun setSelectedTvs(tvs: String) {
        this.tvs = tvs
    }

    fun getMovie(): LiveData<MovieEntity> = movieRepository.getMovie(movie)

    fun getTvs(): LiveData<TvShowEntity> = movieRepository.getTvShow(tvs)
}