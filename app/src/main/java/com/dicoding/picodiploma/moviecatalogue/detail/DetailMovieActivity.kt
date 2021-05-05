package com.dicoding.picodiploma.moviecatalogue.detail

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.moviecatalogue.R
import com.dicoding.picodiploma.moviecatalogue.data.MovieEntity
import com.dicoding.picodiploma.moviecatalogue.data.TvShowEntity
import com.dicoding.picodiploma.moviecatalogue.databinding.ActivityDetailMovieBinding
import com.dicoding.picodiploma.moviecatalogue.databinding.ContentDetailMovieBinding
import com.dicoding.picodiploma.moviecatalogue.utils.DataDummy

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TVS = "extra_tvs"
    }

    private lateinit var detailContentBinding: ContentDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        detailContentBinding = activityDetailMovieBinding.detailContent
        setContentView(activityDetailMovieBinding.root)
        setSupportActionBar(findViewById(R.id.toolbar))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewModelProvider = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailViewModel::class.java]

        val extras = intent.extras
        if (extras !=null) {
            val movie = extras.getString(EXTRA_MOVIE)
            if (movie != null) {
                viewModelProvider.setSelectedMovie(movie)
                populateMovie(viewModelProvider.getMovie())

            }
            val tvs = extras.getString(EXTRA_TVS)
            if (tvs != null) {
                viewModelProvider.setSelectedTvs(tvs)
                populateTvs(viewModelProvider.getTvs())
            }
        }

    }

    private fun populateMovie(movieEntity: MovieEntity) {
        detailContentBinding.textTitle.text = movieEntity.title
        detailContentBinding.textGenre.text = movieEntity.genre
        detailContentBinding.textRelease.text = movieEntity.realeaseDate
        detailContentBinding.textDuration.text = movieEntity.duration
        detailContentBinding.textDesc.text = movieEntity.overview

        Glide.with(this)
                .load(movieEntity.poster)
                .transform(RoundedCorners(20))
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(detailContentBinding.imagePoster)
    }

    private fun populateTvs(tvShowEntity: TvShowEntity) {
        detailContentBinding.textTitle.text = tvShowEntity.title
        detailContentBinding.textGenre.text = tvShowEntity.genre
        detailContentBinding.textDuration.text = tvShowEntity.duration
        detailContentBinding.textDesc.text = tvShowEntity.overview

        Glide.with(this)
                .load(tvShowEntity.poster)
                .transform(RoundedCorners(20))
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(detailContentBinding.imagePoster)
    }
}