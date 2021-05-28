package com.dicoding.picodiploma.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.picodiploma.moviecatalogue.data.source.local.entity.MovieEntity
import com.dicoding.picodiploma.moviecatalogue.data.source.local.entity.TvShowEntity
import com.dicoding.picodiploma.moviecatalogue.data.source.remote.RemoteDataSource
import com.dicoding.picodiploma.moviecatalogue.data.source.remote.response.MovieResponse
import com.dicoding.picodiploma.moviecatalogue.data.source.remote.response.TvShowResponse

class MovieRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    MovieDataSource {

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteDataSource)
            }
    }

    override fun getAllMovie(): LiveData<List<MovieEntity>> {
        val movieResult = MutableLiveData<List<MovieEntity>>()
        remoteDataSource.getAllMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(movieResponse: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in movieResponse) {
                    val movie = MovieEntity(
                        response.id,
                        response.title,
                        response.duration,
                        response.genre,
                        response.release,
                        response.overview,
                        response.poster
                    )
                    movieList.add(movie)
                }
                movieResult.postValue(movieList)
            }
        })
        return movieResult
    }

    override fun getMovieById(id: String): LiveData<MovieEntity> {
        val movieResult = MutableLiveData<MovieEntity>()
        remoteDataSource.getAllMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(movieResponse: List<MovieResponse>) {
                lateinit var movie: MovieEntity
                for (response in movieResponse) {
                    if (response.id == id) {
                        movie = MovieEntity(
                            response.id,
                            response.title,
                            response.duration,
                            response.genre,
                            response.release,
                            response.overview,
                            response.poster
                        )
                        movieResult.postValue(movie)
                    }
                }

            }
        })

        return movieResult
    }

    override fun getAllTvShow(): LiveData<List<TvShowEntity>> {
        val tvShowResult = MutableLiveData<List<TvShowEntity>>()
        remoteDataSource.getAllTvshow(object : RemoteDataSource.LoadTvShowCallback {
            override fun onAllTvShowReceived(tvShowResponse: List<TvShowResponse>) {
                val tvShowList = ArrayList<TvShowEntity>()
                for (response in tvShowResponse) {
                    val tvshow = TvShowEntity(
                        response.id,
                        response.title,
                        response.duration,
                        response.genre,
                        response.overview,
                        response.poster
                    )
                    tvShowList.add(tvshow)
                }
                tvShowResult.postValue(tvShowList)
            }
        })
        return tvShowResult
    }

    override fun getTvShowById(id: String): LiveData<TvShowEntity> {
        val tvShowResult = MutableLiveData<TvShowEntity>()
        remoteDataSource.getAllTvshow(object : RemoteDataSource.LoadTvShowCallback {
            override fun onAllTvShowReceived(tvShowResponse: List<TvShowResponse>) {
                lateinit var tvshow: TvShowEntity
                for (response in tvShowResponse) {
                    if (response.id == id) {
                        tvshow = TvShowEntity(
                            response.id,
                            response.title,
                            response.duration,
                            response.genre,
                            response.overview,
                            response.poster
                        )
                        tvShowResult.postValue(tvshow)
                    }
                }

            }
        })

        return tvShowResult
    }
}