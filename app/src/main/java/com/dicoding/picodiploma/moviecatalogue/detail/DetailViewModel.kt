package com.dicoding.picodiploma.moviecatalogue.detail

import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.moviecatalogue.data.MovieEntity
import com.dicoding.picodiploma.moviecatalogue.data.TvShowEntity
import com.dicoding.picodiploma.moviecatalogue.utils.DataDummy

class DetailViewModel : ViewModel() {
    private lateinit var movie: String
    private lateinit var tvs: String

    fun setSelectedMovie(movie: String) {
        this.movie = movie
    }

    fun setSelectedTvs(tvs: String) {
        this.tvs = tvs
    }

    fun getMovie(): MovieEntity {
        lateinit var movies: MovieEntity
        val moviesEntities = DataDummy.generateDummyMovies()
        for (movieEntity in moviesEntities) {
            if (movieEntity.id == movie) {
                movies = movieEntity
            }
        }
        return movies
    }

    fun getTvs(): TvShowEntity {
        lateinit var tvss: TvShowEntity
        var tvsEntities = DataDummy.generateDummyTvShow()
        for (tvsEntity in tvsEntities) {
            if (tvsEntity.id == tvs) {
                tvss = tvsEntity
            }
        }
        return tvss
    }
}