package com.dicoding.picodiploma.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.dicoding.picodiploma.moviecatalogue.data.NetworkBoundResource
import com.dicoding.picodiploma.moviecatalogue.data.source.local.LocalDataSource
import com.dicoding.picodiploma.moviecatalogue.data.source.local.entity.MovieEntity
import com.dicoding.picodiploma.moviecatalogue.data.source.local.entity.TvShowEntity
import com.dicoding.picodiploma.moviecatalogue.data.source.remote.ApiResponse
import com.dicoding.picodiploma.moviecatalogue.data.source.remote.RemoteDataSource
import com.dicoding.picodiploma.moviecatalogue.data.source.remote.response.MovieResponse
import com.dicoding.picodiploma.moviecatalogue.data.source.remote.response.TvShowResponse
import com.dicoding.picodiploma.moviecatalogue.utils.AppExecutors
import com.dicoding.picodiploma.moviecatalogue.vo.Resource

class MovieRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) :
    MovieDataSource {

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            locaData: LocalDataSource,
            appExecutors: AppExecutors
        ): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteData, locaData, appExecutors).apply {
                    instance = this
                }
            }
    }

    override fun getAllMovie(): LiveData<Resource<List<MovieEntity>>> {
        return object : NetworkBoundResource<List<MovieEntity>, List<MovieResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<List<MovieEntity>> =
                localDataSource.getAllMovies()

            override fun shouldFetch(data: List<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovies()

            public override fun saveCallResult(movieResponses: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in movieResponses) {
                    val movie = MovieEntity(
                        response.id,
                        response.title,
                        response.duration,
                        response.genre,
                        response.release,
                        response.overview,
                        false,
                        response.poster
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()
    }

    override fun getMovieById(id: String): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> =
                localDataSource.getMoviesWithId(id)

            override fun shouldFetch(data: MovieEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovies()

            override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(
                        response.id,
                        response.title,
                        response.duration,
                        response.genre,
                        response.release,
                        response.overview,
                        false,
                        response.poster
                    )
                    movieList.add(movie)
                }

                localDataSource.insertMovies(movieList)
            }


        }.asLiveData()
    }

    override fun getAllTvShow(): LiveData<Resource<List<TvShowEntity>>> {
        return object :
            NetworkBoundResource<List<TvShowEntity>, List<TvShowResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<List<TvShowEntity>> =
                localDataSource.getAllTvShows()

            override fun shouldFetch(data: List<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getAllTvshow()

            public override fun saveCallResult(tvShowResponses: List<TvShowResponse>) {
                val tvShowList = ArrayList<TvShowEntity>()
                for (response in tvShowResponses) {
                    val tvShow = TvShowEntity(
                        response.id,
                        response.title,
                        response.duration,
                        response.genre,
                        response.overview,
                        false,
                        response.poster
                    )
                    tvShowList.add(tvShow)
                }
                localDataSource.insertTvShow(tvShowList)
            }
        }.asLiveData()
    }

    override fun getTvShowById(id: String): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, List<TvShowResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowEntity> =
                localDataSource.getTvShowWithId(id)

            override fun shouldFetch(data: TvShowEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getAllTvshow()

            override fun saveCallResult(data: List<TvShowResponse>) {
                val TvShowList = ArrayList<TvShowEntity>()
                for (response in data) {
                    val tvShow = TvShowEntity(
                        response.id,
                        response.title,
                        response.duration,
                        response.genre,
                        response.overview,
                        false,
                        response.poster
                    )
                    TvShowList.add(tvShow)
                }

                localDataSource.insertTvShow(TvShowList)
            }


        }.asLiveData()
    }

    override fun getMovieFavorite(): LiveData<List<MovieEntity>> =
        localDataSource.getFavoritedMovie()

    override fun setMovieFavorite(movie: MovieEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setMovieFavorite(movie, state) }

    override fun getTvShowFavorite(): LiveData<List<TvShowEntity>> =
        localDataSource.getFavoritedTvShow()

    override fun setTvShowFavorite(tvShow: TvShowEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setTvShowFavorite(tvShow, state) }

}