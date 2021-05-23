package com.dicoding.picodiploma.moviecatalogue.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.picodiploma.moviecatalogue.data.MovieEntity
import com.dicoding.picodiploma.moviecatalogue.data.TvShowEntity
import com.dicoding.picodiploma.moviecatalogue.data.source.MovieRepository
import com.dicoding.picodiploma.moviecatalogue.utils.DataDummy
import com.dicoding.picodiploma.moviecatalogue.viewmodel.DetailViewModel
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`

class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val movieId = dummyMovie.id
    private val dummyTvs = DataDummy.generateDummyTvShow()[0]
    private val tvsId = dummyTvs.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var movieObserver: Observer<MovieEntity>

    @Mock
    private lateinit var tvShowObserver: Observer<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(movieRepository)
        viewModel.setSelectedMovie(movieId)
        viewModel.setSelectedTvs(tvsId)
    }

    @Test
    fun getMovie() {
        val movie = MutableLiveData<MovieEntity>()
        movie.value = dummyMovie

        `when`(movieRepository.getMovie(movieId)).thenReturn(movie)
        val movieEntity = viewModel.getMovie().value as MovieEntity
        verify(movieRepository).getMovie(movieId)
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.id, movieEntity.id)
        assertEquals(dummyMovie.title, movieEntity.title)
        assertEquals(dummyMovie.genre, movieEntity.genre)
        assertEquals(dummyMovie.realease, movieEntity.realease)
        assertEquals(dummyMovie.overview, movieEntity.overview)
        assertEquals(dummyMovie.poster, movieEntity.poster)
        assertEquals(dummyMovie.duration, movieEntity.duration)

        viewModel.getMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
    }

    @Test
    fun getTvs() {
        val tvshow = MutableLiveData<TvShowEntity>()
        tvshow.value = dummyTvs

        `when`(movieRepository.getTvShow(tvsId)).thenReturn(tvshow)
        val tvShowEntity = viewModel.getTvs().value as TvShowEntity
        verify(movieRepository).getTvShow(tvsId)
        assertNotNull(tvShowEntity)
        assertEquals(dummyMovie.id, tvShowEntity.id)
        assertEquals(dummyMovie.title, tvShowEntity.title)
        assertEquals(dummyMovie.genre, tvShowEntity.genre)
        assertEquals(dummyMovie.overview, tvShowEntity.overview)
        assertEquals(dummyMovie.poster, tvShowEntity.poster)
        assertEquals(dummyMovie.duration, tvShowEntity.duration)

        viewModel.getTvs().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyTvs)
    }
}