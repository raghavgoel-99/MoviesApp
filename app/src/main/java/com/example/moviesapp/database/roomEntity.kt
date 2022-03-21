package com.example.moviesapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//data class Item(
//    @PrimaryKey val uid: Int,
//    @ColumnInfo(name = "first_name") val firstName: String?,
//    @ColumnInfo(name = "last_name") val lastName: String?
//)
@Entity
data class MoviesData(
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name="id") val id:Int=0,
    @ColumnInfo (name = "items")val items: List<Items>?)

@Entity(tableName = "movies")
data class Items(@PrimaryKey (autoGenerate = true) @ColumnInfo(name="sno") val sno:Int=0,
                 @ColumnInfo(name="id")val id: String?,
                 @ColumnInfo(name = "rank") val rank: String?,
                 @ColumnInfo(name = "title")val title: String?,
                 @ColumnInfo(name = "fullTitle")val fullTitle: String?,
                 @ColumnInfo(name = "year")val year: String?,
                 @ColumnInfo(name = "image")val image: String?,
                 @ColumnInfo(name = "crew") val crew: String?,
                 @ColumnInfo(name = "imDbRating")val imDbRating: String?,
                 @ColumnInfo(name = "imDbRatingCount")val imDbRatingCount: String?
)