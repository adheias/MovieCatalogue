package com.dicoding.picodiploma.moviecatalogue.tvshow

import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.moviecatalogue.data.TvShowEntity
import com.dicoding.picodiploma.moviecatalogue.utils.DataDummy

class TvsViewModel : ViewModel() {
    fun getTvs(): List<TvShowEntity> = DataDummy.generateDummyTvShow()
}