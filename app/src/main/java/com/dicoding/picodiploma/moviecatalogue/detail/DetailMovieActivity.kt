package com.dicoding.picodiploma.moviecatalogue.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
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
import com.dicoding.picodiploma.moviecatalogue.vo.Status
import kotlinx.android.synthetic.main.content_detail_movie.*


class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TVS = "extra_tvs"
    }

    private lateinit var detailContentBinding: ContentDetailMovieBinding
    private lateinit var viewModel: DetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailContentBinding = ContentDetailMovieBinding.inflate(layoutInflater)
        setContentView(detailContentBinding.root)

        supportActionBar?.title = "Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        intent.extras?.let {
            it.getString(EXTRA_MOVIE)?.apply {

                viewModel.setSelectedMovie(this)
                viewModel.movie.observe(this@DetailMovieActivity, { movies ->
                    if (movies != null) {
                        when (movies.status) {
                            Status.LOADING -> detailContentBinding.progressBar.visibility =
                                View.VISIBLE
                            Status.SUCCESS -> if (movies.data != null) {
                                detailContentBinding.progressBar.visibility = View.GONE

                                populateMovie(movies.data)
                            }
                            Status.ERROR -> {
                                detailContentBinding.progressBar.visibility = View.GONE
                                Toast.makeText(
                                    applicationContext,
                                    "Terjadi Kesalahan",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                })
            }
            it.getString(EXTRA_TVS)?.apply {

                viewModel.setSelectedTvs(this)
                viewModel.tvShow.observe(this@DetailMovieActivity, { tvshow ->
                    if (tvshow != null) {
                        when (tvshow.status) {
                            Status.LOADING -> detailContentBinding.progressBar.visibility =
                                View.VISIBLE
                            Status.SUCCESS -> if (tvshow.data != null) {
                                detailContentBinding.progressBar.visibility = View.GONE

                                populateTvShow(tvshow.data)
                            }
                            Status.ERROR -> {
                                detailContentBinding.progressBar.visibility = View.GONE
                                Toast.makeText(
                                    applicationContext,
                                    "Terjadi Kesalahan",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
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

        var statusFavorite = movieEntity.favorited
        setStatusFavorite(statusFavorite)
        fab_add.setOnClickListener {
            statusFavorite = !statusFavorite
            viewModel.setFavoriteMovie(movieEntity, statusFavorite)
            setStatusFavorite(statusFavorite)
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

        var statusFavorite = tvShowEntity.favorited
        setStatusFavorite(statusFavorite)
        fab_add.setOnClickListener {
            statusFavorite = !statusFavorite
            viewModel.setFavoriteTvShow(tvShowEntity, statusFavorite)
            setStatusFavorite(statusFavorite)
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