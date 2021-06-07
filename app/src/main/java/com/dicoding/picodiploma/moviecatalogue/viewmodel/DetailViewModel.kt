package com.dicoding.picodiploma.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.moviecatalogue.data.source.MovieRepository
import com.dicoding.picodiploma.moviecatalogue.data.source.local.entity.MovieEntity
import com.dicoding.picodiploma.moviecatalogue.data.source.local.entity.TvShowEntity
import com.dicoding.picodiploma.moviecatalogue.vo.Resource

class DetailViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    val movieId: MutableLiveData<String>()
    val tvShowId: MutableLiveData<String>()

    fun setSelectedMovie(movieId: String) {
        this.movieId.value = movieId
    }

    fun setSelectedTvs(tvShowId: String) {
        this.tvShowId.value = tvShowId
    }

    var movie: LiveData<Resource<MovieEntity>> = Transformations.switchMap(movieId) { mMovieId ->
        movieRepository.getMovieById(mMovieId)
    }

    var tvShow: LiveData<Resource<TvShowEntity>> =
        Transformations.switchMap(tvShowId) { mTvShowId ->
            movieRepository.getTvShowById(mTvShowId)
        }

    fun setFavorite() {
        val movieResources = movie.value
        val tvShowResource = tvShow.value

        if (movieResources != null) {
            val movieWithData = movieResources.data

            if (movieResources != null) {
                val newState = !movieWithData.favorited
                movieRepository.setMovieFavorite(movieWithData, newState)
            }
        }

        if (tvShowResource != null) {
            val tvShowWithData = tvShowResource.data

            if (tvShowResource != null) {
                val newState = !tvShowWithData.favorited
                movieRepository.setMovieFavorite(tvShowWithData, newState)
            }
        }
    }


}