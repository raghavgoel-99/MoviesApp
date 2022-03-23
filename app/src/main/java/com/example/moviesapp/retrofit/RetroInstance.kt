package com.example.moviesapp.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {
    companion object {
        val Base_URL = "https://api.themoviedb.org/3/"
        fun getretrointance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}