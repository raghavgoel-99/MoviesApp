package com.example.moviesapp.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {
    companion object{
        val Base_URL="https://imdb-api.com/en/API/Top250Movies/"
        fun getretrointance(): Retrofit{
            return Retrofit.Builder()
                .baseUrl(Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}