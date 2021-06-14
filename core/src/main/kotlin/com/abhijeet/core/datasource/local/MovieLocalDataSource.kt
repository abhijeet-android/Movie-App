package com.abhijeet.core.datasource.local


import com.abhijeet.core.data.entities.Movie
import com.abhijeet.core.database.MovieDao
import javax.inject.Inject

class MovieLocalDataSource @Inject constructor(
    private val movieDao: MovieDao
) {

    suspend fun insertAll(movies: List<Movie>) {
        movieDao.insertAll(movies)
    }

    suspend fun getMovieById(movieId: Int) = movieDao.getMovieById(movieId)
}