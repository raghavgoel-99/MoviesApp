package com.example.moviesapp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapp.database.*
import com.example.moviesapp.retrofit.RetroInstance
import com.example.moviesapp.retrofit.RetroInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GenreViewModel(var application: Application) : ViewModel() {

    var liveDataList: MutableLiveData<List<Genres>>
    init {
        liveDataList = MutableLiveData()
    }
    fun getlivegenredata(): MutableLiveData<List<Genres>> {
        return liveDataList
    }

    fun makeApiCallGenre() {
        println("fetching")
        val retroInstance = RetroInstance.getretrointance()
        val retroService = retroInstance.create(RetroInterface::class.java)

        val call = retroService.getGenreList("18b04b2c0c2663bd59770c6f6cc9f5a3")
        call.enqueue(object : Callback<GenresData> {
            override fun onResponse(
                call: Call<GenresData>,
                response: Response<GenresData>
            ) {
                liveDataList?.postValue(response.body()?.genres)
                println(response.body())
                val userDao = RoomAppDb.getAppDatabase(application)?.userDao()
                userDao.insertGenre(response.body()?.genres)
//                userDao.insertAll(response.body()?.results)
            }
            override fun onFailure(call: Call<GenresData>, t: Throwable) {

            }

        }
        )
    }
}