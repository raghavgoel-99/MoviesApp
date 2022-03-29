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

    var liveDataList: MutableLiveData<MutableList<Items>>
    var pagenumber: Int = 1
    var currentdata: MutableList<Items>? = null

    init {
        liveDataList = MutableLiveData()
    }

    fun getlivedata(): MutableLiveData<MutableList<Items>> {
        return liveDataList
    }
    fun refresh(genreId: Int?)
    {
        val userDao = RoomAppDb.getAppDatabase(application)?.userDao()
        val list = userDao.getAll(genreId)
        liveDataList?.postValue(list)
    }
    fun makeApiCall(genreId: Int) {
        println("fetching")
        val retroInstance = RetroInstance.getretrointance()
        val retroService = retroInstance.create(RetroInterface::class.java)
        val call =
            retroService.getMovieList(pagenumber, "18b04b2c0c2663bd59770c6f6cc9f5a3", genreId)
        call.enqueue(object : Callback<MoviesData> {
            override fun onResponse(
                call: Call<MoviesData>,
                response: Response<MoviesData>
            ) {

                val userDao = RoomAppDb.getAppDatabase(application)?.userDao()
                if (response.body() != null) {
                    println("fetching" + pagenumber)
                    pagenumber++
                    println(response.body())
                    response.body()?.results?.forEach {
                        it.genreid=genreId
                    }
                    userDao.insertAll(response.body()?.results)

                }
                val list = userDao.getAll(genreId)
                liveDataList?.postValue(list)
            }

            override fun onFailure(call: Call<MoviesData>, t: Throwable) {

                val userDao = RoomAppDb.getAppDatabase(application)?.userDao()
                val list = userDao.getAll(genreId)
                liveDataList?.postValue(list)
            }
        })
    }

    fun DeletAll() {
        val userDao = RoomAppDb.getAppDatabase(application)?.userDao()
        userDao.deleteAll()
    }
}
