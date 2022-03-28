package com.example.moviesapp.database

import androidx.room.*

//import com.example.moviesapp.data.MoviesData

@Dao
interface roomDao {
    @Query("SELECT * FROM moviesList")
    fun getAll(): MutableList<Items>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: MutableList<Items>?)

    @Query("DELETE FROM moviesList")
    fun deleteAll()

    @Query("UPDATE moviesList SET liked_status = :status WHERE id =:id")
    fun update(status: Boolean?, id: Int?)
}