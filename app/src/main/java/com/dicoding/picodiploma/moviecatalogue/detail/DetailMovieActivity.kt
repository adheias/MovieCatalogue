package com.dicoding.picodiploma.moviecatalogue.detail

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.picodiploma.moviecatalogue.R

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_COURSE = "extra_course"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        setSupportActionBar(findViewById(R.id.toolbar))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}