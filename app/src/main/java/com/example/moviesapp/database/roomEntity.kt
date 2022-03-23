package com.example.moviesapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MoviesData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "result") val results: MutableList<Items>?
)

@Entity(tableName = "moviesList")
data class Items(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "sno") val sno: Int = 0,
    @ColumnInfo(name = "id") val id: Int?,
    @ColumnInfo(name = "adult") val adult: String?,
    @ColumnInfo(name = "original_language") val original_language: String?,
    @ColumnInfo(name = "overview") val overview: String?,
    @ColumnInfo(name = "poster_path") val poster_path: String?,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "original_title") val original_title: String?,
    @ColumnInfo(name = "release_date") val release_date: String?,
    @ColumnInfo(name = "backdrop_path") val backdrop_path: String?,
    @ColumnInfo(name = "vote_average") val vote_average: String?,
    @ColumnInfo(name = "vote_count") val vote_count: String?
)

data class GenresData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "genres") val genres: MutableList<Genres>?
)

@Entity(tableName = "genresList")
data class Genres(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "sno") val sno: Int = 0,
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String?
)