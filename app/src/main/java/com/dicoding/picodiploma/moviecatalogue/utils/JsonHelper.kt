package com.dicoding.picodiploma.moviecatalogue.utils

import android.content.Context
import com.dicoding.picodiploma.moviecatalogue.data.source.remote.response.MovieResponse
import com.dicoding.picodiploma.moviecatalogue.data.source.remote.response.TvShowResponse
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val context: Context) {

    private fun parsingFileToString(fileName: String): String? {
        return try {
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)

        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    fun loadMovie(): List<MovieResponse> {
        val list = ArrayList<MovieResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("MovieAssets.json").toString())
            val listArray = responseObject.getJSONArray("movies")
            for (i in 0 until listArray.length()) {
                val movie = listArray.getJSONObject(i)

                val id = movie.getString("id")
                val title = movie.getString("title")
                val release = movie.getString("release")
                val genre = movie.getString("genre")
                val duration = movie.getString("duration")
                val overview = movie.getString("overview")
                val poster = movie.getString("poster")

                val movieResponse =
                    MovieResponse(id, title, release, genre, duration, overview, poster)
                list.add(movieResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

    fun loadTvShow(): List<TvShowResponse> {
        val list = ArrayList<TvShowResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("TvShowAssets.json").toString())
            val listArray = responseObject.getJSONArray("tvshow")
            for (i in 0 until listArray.length()) {
                val tvshow = listArray.getJSONObject(i)

                val id = tvshow.getString("id")
                val title = tvshow.getString("title")
                val genre = tvshow.getString("genre")
                val duration = tvshow.getString("duration")
                val overview = tvshow.getString("overview")
                val poster = tvshow.getString("poster")

                val tvshowResponse = TvShowResponse(id, title, genre, duration, overview, poster)
                list.add(tvshowResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }
}