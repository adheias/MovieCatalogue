package com.dicoding.picodiploma.moviecatalogue.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.moviecatalogue.R
import com.dicoding.picodiploma.moviecatalogue.data.source.local.entity.MovieEntity
import com.dicoding.picodiploma.moviecatalogue.data.source.local.entity.TvShowEntity
import com.dicoding.picodiploma.moviecatalogue.databinding.ContentDetailMovieBinding
import com.dicoding.picodiploma.moviecatalogue.viewmodel.DetailViewModel
import com.dicoding.picodiploma.moviecatalogue.viewmodel.ViewModelFactory


class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TVS = "extra_tvs"
    }

    private lateinit var detailContentBinding: ContentDetailMovieBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailContentBinding = ContentDetailMovieBinding.inflate(layoutInflater)
        setContentView(detailContentBinding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        val viewModelProvider = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        intent.extras?.let {
            it.getString(EXTRA_MOVIE)?.apply {

                detailContentBinding.progressBar.visibility = View.VISIBLE
                detailContentBinding.imagePoster.visibility = View.GONE
                detailContentBinding.textDesc.visibility = View.GONE
                detailContentBinding.textDuration.visibility = View.GONE
                detailContentBinding.textGenre.visibility = View.GONE
                detailContentBinding.textRelease.visibility = View.GONE
                detailContentBinding.textTitle.visibility = View.GONE
                detailContentBinding.textView.visibility = View.GONE

                viewModelProvider.setSelectedMovie(this)
                viewModelProvider.getMovie().observe(this@DetailMovieActivity, { movies ->
                    detailContentBinding.progressBar.visibility = View.GONE
                    detailContentBinding.imagePoster.visibility = View.VISIBLE
                    detailContentBinding.textDesc.visibility = View.VISIBLE
                    detailContentBinding.textDuration.visibility = View.VISIBLE
                    detailContentBinding.textGenre.visibility = View.VISIBLE
                    detailContentBinding.textRelease.visibility = View.VISIBLE
                    detailContentBinding.textTitle.visibility = View.VISIBLE
                    detailContentBinding.textView.visibility = View.VISIBLE
                    populateMovie(movies)
                })

            }
            it.getString(EXTRA_TVS)?.apply {
                detailContentBinding.progressBar.visibility = View.VISIBLE
                detailContentBinding.imagePoster.visibility = View.GONE
                detailContentBinding.textDesc.visibility = View.GONE
                detailContentBinding.textDuration.visibility = View.GONE
                detailContentBinding.textGenre.visibility = View.GONE
                detailContentBinding.textRelease.visibility = View.GONE
                detailContentBinding.textTitle.visibility = View.GONE
                detailContentBinding.textView.visibility = View.GONE

                viewModelProvider.setSelectedTvs(this)
                viewModelProvider.getTvs().observe(this@DetailMovieActivity, { tvShow ->
                    detailContentBinding.progressBar.visibility = View.GONE
                    detailContentBinding.imagePoster.visibility = View.VISIBLE
                    detailContentBinding.textDesc.visibility = View.VISIBLE
                    detailContentBinding.textDuration.visibility = View.VISIBLE
                    detailContentBinding.textGenre.visibility = View.VISIBLE
                    detailContentBinding.textRelease.visibility = View.VISIBLE
                    detailContentBinding.textTitle.visibility = View.VISIBLE
                    detailContentBinding.textView.visibility = View.VISIBLE
                    populateTvShow(tvShow)
                })
            }
        }

    }

    private fun populateMovie(movieEntity: MovieEntity) {
        detailContentBinding.apply {
            detailContentBinding.textTitle.text = movieEntity.title
            detailContentBinding.textGenre.text = movieEntity.genre
            detailContentBinding.textRelease.text = movieEntity.release
            detailContentBinding.textDuration.text = movieEntity.duration
            detailContentBinding.textDesc.text = movieEntity.overview

            Glide.with(this@DetailMovieActivity)
                .load(movieEntity.poster)
                .transform(RoundedCorners(20))
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(detailContentBinding.imagePoster)
        }
    }

    private fun populateTvShow(tvShowEntity: TvShowEntity) {
        detailContentBinding.apply {
            detailContentBinding.textTitle.text = tvShowEntity.title
            detailContentBinding.textGenre.text = tvShowEntity.genre
            detailContentBinding.textDuration.text = tvShowEntity.duration
            detailContentBinding.textDesc.text = tvShowEntity.overview

            Glide.with(this@DetailMovieActivity)
                .load(tvShowEntity.poster)
                .transform(RoundedCorners(20))
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(detailContentBinding.imagePoster)
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            detailContentBinding.fabAdd.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else {
            detailContentBinding.fabAdd.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }
}