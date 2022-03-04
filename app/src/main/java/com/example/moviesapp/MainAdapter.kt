package com.example.moviesapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.movie_model.view.*
import java.lang.reflect.Array.get
import java.nio.file.Files.size

class MainAdapter(val movies:MoviesList) : RecyclerView.Adapter<CustomViewHolder>() {

    override fun getItemCount(): Int {
        return movies?.items.count()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val currentmovie = layoutInflater.inflate(R.layout.movie_model, parent, false)
        return CustomViewHolder(currentmovie)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
//    val movie = movies.items.get(position)
//        holder.v.Movie_name.text = movie?.title
//        Glide.with(holder.v).load(movie?.image).into(holder.v.Movie_Icon)
//        holder.v.rating.text = movie?.imDbRating
        holder.bind(movies.items.get(position))
    }
}

class CustomViewHolder(val v: View) : RecyclerView.ViewHolder(v) {
    val movieicon: ImageView = v.findViewById(R.id.Movie_Icon)
    val moviename: TextView = v.findViewById(R.id.Movie_name)
    val movierating: TextView = v.findViewById(R.id.rating)

    fun bind(data: Items) {
        moviename.text = data.title
        movierating.text = data.imDbRating

            Glide.with(movieicon)
                .load(data.image)
                .circleCrop()
                .into(movieicon)

    }
}