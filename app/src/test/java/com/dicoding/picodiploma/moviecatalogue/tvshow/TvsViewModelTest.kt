package com.dicoding.picodiploma.moviecatalogue.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.picodiploma.moviecatalogue.data.source.MovieRepository
import com.dicoding.picodiploma.moviecatalogue.data.source.local.entity.TvShowEntity
import com.dicoding.picodiploma.moviecatalogue.utils.DataDummy
import com.dicoding.picodiploma.moviecatalogue.viewmodel.TvsViewModel
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvsViewModelTest {

    private lateinit var viewModel: TvsViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<List<TvShowEntity>>

    @Before
    fun setUp() {
        viewModel = TvsViewModel(movieRepository)
    }

    @Test
    fun getMovies() {
        val dummyTvs = DataDummy.generateDummyTvShow()
        val tvShow = MutableLiveData<List<TvShowEntity>>()
        tvShow.value = dummyTvs

        `when`(movieRepository.getAllTvShow()).thenReturn(tvShow)
        val tvShowEntity = viewModel.getTvs().value
        verify(movieRepository).getAllTvShow()
        assertNotNull(tvShowEntity)
        assertEquals(10, tvShowEntity?.size)

        viewModel.getTvs().observeForever(observer)
        verify(observer).onChanged(dummyTvs)
    }
}