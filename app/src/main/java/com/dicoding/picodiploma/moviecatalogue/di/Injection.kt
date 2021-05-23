package com.dicoding.picodiploma.moviecatalogue.di

import android.content.Context
import com.dicoding.picodiploma.moviecatalogue.data.source.MovieRepository
import com.dicoding.picodiploma.moviecatalogue.data.source.remote.RemoteDataSource
import com.dicoding.picodiploma.moviecatalogue.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): MovieRepository {

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))

        return MovieRepository.getInstance(remoteDataSource)
    }
}