package com.example.moviesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.R
import com.example.moviesapp.database.Items

//latetst
class MainAdapter : RecyclerView.Adapter<CustomViewHolder>() {

    private var movieList: List<Items>? = null

    override fun getItemCount(): Int {
        if (movieList == null)
            return 0
        else
            return movieList?.size!!
    }

    fun setMovieList(moviesList: List<Items>) {
        this.movieList = moviesList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val currentmovie = layoutInflater.inflate(R.layout.movie_model, parent, false)
        return CustomViewHolder(currentmovie)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(movieList?.get(position)!!)
    }
}

class CustomViewHolder(val v: View) : RecyclerView.ViewHolder(v) {
    val movieicon: ImageView = v.findViewById(R.id.Movie_Icon)
    val moviename: TextView = v.findViewById(R.id.Movie_name)
    val movierating: TextView = v.findViewById(R.id.rating)
    val releaseDate: TextView = v.findViewById(R.id.releaseDate)

    fun bind(data: Items?) {
        moviename.text = "Name : " + data?.title
        movierating.text = "Rating : " + data?.vote_average
        releaseDate.text = "Date : " + data?.release_date
        Glide.with(movieicon)
            .load("https://image.tmdb.org/t/p/w185//" + data?.poster_path)
            .into(movieicon)

    }
}