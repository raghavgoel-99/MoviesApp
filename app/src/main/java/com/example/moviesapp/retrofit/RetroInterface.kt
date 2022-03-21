package com.example.moviesapp.retrofit

//import com.example.moviesapp.database.MoviesData
import com.example.moviesapp.database.MoviesData
import retrofit2.Call
import retrofit2.http.GET

interface RetroInterface {
    @GET("k_1n878jkv")
    fun getMovieList(): Call<MoviesData>
    //latest

}