package com.example.moviesapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.moviesapp.database.Items
import com.example.moviesapp.database.MoviesData
import com.example.moviesapp.database.RoomAppDb
import com.example.moviesapp.retrofit.RetroInstance
import com.example.moviesapp.retrofit.RetroInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class MainActivityViewModel(var application: Application) : ViewModel() {

    var liveDataList: MutableLiveData<List<Items>>

    init {
        liveDataList = MutableLiveData()
    }
    fun getlivedata(): MutableLiveData<List<Items>> {
        val userDao = RoomAppDb.getAppDatabase(application)?.userDao()
        val list = userDao.getAll()
        liveDataList?.postValue(list)
        return liveDataList
    }

    fun makeApiCall() {
        val retroInstance = RetroInstance.getretrointance()
        val retroService = retroInstance.create(RetroInterface::class.java)
        val call = retroService.getMovieList()
        call.enqueue(object : Callback<MoviesData> {
            override fun onResponse(
                call: Call<MoviesData>,
                response: Response<MoviesData>
            ) {
                liveDataList?.postValue(response.body()?.items)
                println(response.body()?.items)
                if (response.body() != null) {
                    val userDao = RoomAppDb.getAppDatabase(application)?.userDao()
                    userDao.insertAll(response.body()?.items)
                    println(response.body()?.items)
                }
            }
            override fun onFailure(call: Call<MoviesData>, t: Throwable) {
                liveDataList?.postValue(null)
            }

        }
        )
    }
}
