package com.dicoding.picodiploma.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.moviecatalogue.data.TvShowEntity
import com.dicoding.picodiploma.moviecatalogue.data.source.MovieRepository

class TvsViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getTvs(): LiveData<List<TvShowEntity>> = movieRepository.getAllTvShow()
}