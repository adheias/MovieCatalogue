package com.dicoding.picodiploma.moviecatalogue.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.picodiploma.moviecatalogue.R
import com.dicoding.picodiploma.moviecatalogue.home.HomeActivity

class SplashScreen : AppCompatActivity() {
    private var timeOut = 2000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        loadSplashScreen()
    }

    private fun loadSplashScreen() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashScreen, HomeActivity::class.java))
            finish()
        }, timeOut.toLong())
    }
}