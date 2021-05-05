package com.dicoding.picodiploma.moviecatalogue.detail

import com.dicoding.picodiploma.moviecatalogue.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val movieId = dummyMovie.id
    private val dummyTvs = DataDummy.generateDummyTvShow()[0]
    private val tvsId = dummyTvs.id

    @Before
    fun setUp() {
        viewModel = DetailViewModel()
        viewModel.setSelectedMovie(movieId)
        viewModel.setSelectedTvs(tvsId)
    }

    @Test
    fun getMovie() {
        viewModel.setSelectedMovie(dummyMovie.id)
        val movieEntity = viewModel.getMovie()
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.id, movieEntity.id)
        assertEquals(dummyMovie.title, movieEntity.title)
        assertEquals(dummyMovie.genre, movieEntity.genre)
        assertEquals(dummyMovie.realeaseDate, movieEntity.realeaseDate)
        assertEquals(dummyMovie.overview, movieEntity.overview)
        assertEquals(dummyMovie.poster, movieEntity.poster)
        assertEquals(dummyMovie.duration, movieEntity.duration)
    }

    @Test
    fun getTvs() {
        viewModel.setSelectedTvs(dummyTvs.id)
        val tvsEntity = viewModel.getMovie()
        assertNotNull(tvsEntity)
        assertEquals(dummyMovie.id, tvsEntity.id)
        assertEquals(dummyMovie.title, tvsEntity.title)
        assertEquals(dummyMovie.genre, tvsEntity.genre)
        assertEquals(dummyMovie.realeaseDate, tvsEntity.realeaseDate)
        assertEquals(dummyMovie.overview, tvsEntity.overview)
        assertEquals(dummyMovie.poster, tvsEntity.poster)
        assertEquals(dummyMovie.duration, tvsEntity.duration)
    }
}