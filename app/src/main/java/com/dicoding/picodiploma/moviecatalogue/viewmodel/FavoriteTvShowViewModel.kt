package com.dicoding.picodiploma.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.moviecatalogue.data.source.MovieRepository
import com.dicoding.picodiploma.moviecatalogue.data.source.local.entity.TvShowEntity

class FavoriteTvShowViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getTvShowFavorite(): LiveData<List<TvShowEntity>> = movieRepository.getTvShowFavorite()
}