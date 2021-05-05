package com.dicoding.picodiploma.moviecatalogue.tvshow

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class TvsViewModelTest {

    private lateinit var viewModel: TvsViewModel

    @Before
    fun setUp() {
        viewModel = TvsViewModel()
    }

    @Test
    fun getMovies() {
        val tvsEntity = viewModel.getTvs()
        assertNotNull(tvsEntity)
        assertEquals(10, tvsEntity.size.toLong())
    }
}