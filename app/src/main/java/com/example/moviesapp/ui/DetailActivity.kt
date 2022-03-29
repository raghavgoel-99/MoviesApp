package com.example.moviesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.bumptech.glide.Glide
import com.example.moviesapp.R
import com.example.moviesapp.database.RoomAppDb
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    var movieName: String? = null
    var movieImage: String? = null
    var date: String? = null
    var moviewDisc: String? = null
    var Id: String? = null
    var status: String?=null
    private var i:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        movieName = intent.getStringExtra("title")
        movieImage = intent.getStringExtra("poster_path")
        date = intent.getStringExtra("date")
        moviewDisc = intent.getStringExtra("overview")
        Id= intent.getStringExtra("id")
        status=intent.getStringExtra("liked_status")

        overview.setText(moviewDisc)
        name.setText(movieName)
        Glide.with(Image)
            .load("https://image.tmdb.org/t/p/w185//"+movieImage)
            .into(Image)
        release_date.setText(date)

        Image.setOnClickListener{
            i++;
            val handler=Handler()
            handler.postDelayed({
                if(i==2)
                {
                    liked()
                }
                i=0
            },300)
        }
        Heart.setOnClickListener{
            notLiked()
        }
        Heart_Frame.setOnClickListener{
            liked()
        }
    }

    override fun onStart() {
        super.onStart()
        println(status)
        if(status=="true")
        {
            liked()
        }
        else
        {
            notLiked()
        }
    }
    fun liked()
    {
        Heart_Frame.setVisibility(View.GONE)
        Heart.setVisibility(View.VISIBLE)
        val userDao = RoomAppDb.getAppDatabase(application)?.userDao()
        userDao.update(true,Id?.toInt())
    }
    fun notLiked()
    {
        Heart.setVisibility(View.GONE)
        Heart_Frame.setVisibility(View.VISIBLE)
        val userDao = RoomAppDb.getAppDatabase(application)?.userDao()
        userDao.update(false,Id?.toInt())
    }

}