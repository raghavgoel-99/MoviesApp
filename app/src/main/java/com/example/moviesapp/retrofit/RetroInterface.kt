package com.example.moviesapp.retrofit

//import com.example.moviesapp.database.MoviesData
import com.example.moviesapp.database.GenresData
import com.example.moviesapp.database.Items
import com.example.moviesapp.database.MoviesData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroInterface {
    @GET("discover/movie")
    fun getMovieList(
        @Query("page") page: Int,
        @Query("api_key") api_key: String,
        @Query("with_genres") with_genres: Int
    ): Call<MoviesData>

    //latest
    @GET("genre/movie/list")
    fun getGenreList(
        @Query("api_key") api_key: String
    ): Call<GenresData>

}