package com.dicoding.picodiploma.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.moviecatalogue.data.source.MovieRepository
import com.dicoding.picodiploma.moviecatalogue.data.source.local.entity.TvShowEntity
import com.dicoding.picodiploma.moviecatalogue.vo.Resource

class TvsViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getTvs(): LiveData<Resource<List<TvShowEntity>>> = movieRepository.getAllTvShow()
}