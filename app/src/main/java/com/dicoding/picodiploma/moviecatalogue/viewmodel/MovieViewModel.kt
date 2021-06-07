package com.dicoding.picodiploma.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

import com.dicoding.picodiploma.moviecatalogue.data.source.MovieRepository
import com.dicoding.picodiploma.moviecatalogue.data.source.local.entity.MovieEntity
import com.dicoding.picodiploma.moviecatalogue.vo.Resource

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getMovies(): LiveData<Resource<List<MovieEntity>>> = movieRepository.getAllMovie()
}