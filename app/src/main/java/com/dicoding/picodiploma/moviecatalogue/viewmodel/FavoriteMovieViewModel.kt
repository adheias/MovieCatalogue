package com.dicoding.picodiploma.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.moviecatalogue.data.source.MovieRepository
import com.dicoding.picodiploma.moviecatalogue.data.source.local.entity.MovieEntity

class FavoriteMovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getMovieFavorite(): LiveData<List<MovieEntity>> = movieRepository.getMovieFavorite()
}