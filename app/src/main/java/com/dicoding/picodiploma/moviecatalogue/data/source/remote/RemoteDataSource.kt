package com.dicoding.picodiploma.moviecatalogue.data.source.remote

import android.os.Handler
import android.os.Looper
import com.dicoding.picodiploma.moviecatalogue.data.source.remote.response.MovieResponse
import com.dicoding.picodiploma.moviecatalogue.data.source.remote.response.TvShowResponse
import com.dicoding.picodiploma.moviecatalogue.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    private val handler = Handler(Looper.getMainLooper())

    companion object {

        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: synchronized(this) {
                    instance ?: RemoteDataSource(helper).apply { instance = this }
                }
            }
    }

    fun getAllMovies(callback: LoadMoviesCallback) {
        handler.postDelayed(
            { callback.onAllMoviesReceived(jsonHelper.loadMovie()) },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    fun getAllTvshow(callback: LoadTvShowCallback) {
        handler.postDelayed(
            { callback.onAllTvShowReceived(jsonHelper.loadTvShow()) },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(movieResponse: List<MovieResponse>)
    }

    interface LoadTvShowCallback {
        fun onAllTvShowReceived(tvShowResponse: List<TvShowResponse>)
    }
}