package com.abhijeet.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.abhijeet.core.BuildConfig
import com.abhijeet.core.data.entities.Movie

@Database(
    entities = [Movie::class],
    exportSchema = false,
    version = BuildConfig.MOVIE_DATABASE_VERSION
)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}