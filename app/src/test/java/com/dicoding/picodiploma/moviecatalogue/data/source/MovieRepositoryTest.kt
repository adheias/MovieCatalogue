package com.dicoding.picodiploma.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dicoding.picodiploma.moviecatalogue.data.source.remote.RemoteDataSource
import com.dicoding.picodiploma.moviecatalogue.utils.DataDummy
import com.dicoding.picodiploma.moviecatalogue.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock


class MovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val movieRepository = FakeMovieRepository(remote)

    private val movieResponses = DataDummy.generateRemoteDummyMovies()
    private val movieId = movieResponses[0].id
    private val tvShowResponses = DataDummy.generateRemoteDummyTvShow()
    private val tvShowId = tvShowResponses[0].id

    @Test
    fun getAllMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback)
                .onAllMoviesReceived(movieResponses)
            null
        }.`when`(remote).getAllMovies(any())
        val movieEntity = LiveDataTestUtil.getValue(movieRepository.getAllMovie())
        verify(remote).getAllMovies(any())
        assertNotNull(movieEntity)
        assertEquals(movieResponses.size.toLong(), movieEntity.size.toLong())
    }

    @Test
    fun getAllTvShow() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowCallback)
                .onAllTvShowReceived(tvShowResponses)
            null
        }.`when`(remote).getAllTvshow(any())
        val tvShowEntity = LiveDataTestUtil.getValue(movieRepository.getAllTvShow())
        verify(remote).getAllTvshow(any())
        assertNotNull(tvShowEntity)
        assertEquals(tvShowResponses.size.toLong(), tvShowEntity.size.toLong())
    }

    @Test
    fun getMoviesById() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback)
                .onAllMoviesReceived(movieResponses)
            null
        }.`when`(remote).getAllMovies(any())
        val movieEntity = LiveDataTestUtil.getValue(movieRepository.getMovieById(movieId))
        verify(remote).getAllMovies(any())
        assertNotNull(movieEntity)
        assertNotNull(movieEntity.title)
        assertEquals(movieResponses[0].title, movieEntity.title)
    }


    @Test
    fun getTvShowById() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowCallback)
                .onAllTvShowReceived(tvShowResponses)
            null
        }.`when`(remote).getAllTvshow(any())
        val tvShowEntity = LiveDataTestUtil.getValue(movieRepository.getTvShowById(tvShowId))
        verify(remote).getAllTvshow(any())
        assertNotNull(tvShowEntity)
        assertNotNull(tvShowEntity.title)
        assertEquals(tvShowResponses[0].title, tvShowEntity.title)
    }
}