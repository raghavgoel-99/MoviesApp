package com.example.moviesapp.database

import android.content.ClipData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

//import com.example.moviesapp.data.MoviesData

@Dao
interface roomDao {

    @Query("SELECT * FROM moviesList")
    fun getAll(): MutableList<Items>

    @Insert
    fun insertAll(list: MutableList<Items>?)

    @Query("DELETE FROM moviesList")
    fun deleteAll()

}