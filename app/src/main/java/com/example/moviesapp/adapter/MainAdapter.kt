package com.example.moviesapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.ui.DetailActivity
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
        holder.v.setOnClickListener {
            val intent = Intent(it.context, DetailActivity::class.java)
            intent.putExtra("title", movieList?.get(position)!!.title.toString())
            intent.putExtra("overview", movieList?.get(position)!!.overview.toString())
            intent.putExtra("poster_path", movieList?.get(position)!!.poster_path.toString())
            intent.putExtra("date", movieList?.get(position)!!.release_date.toString())
            intent.putExtra("id",movieList?.get(position)!!.id.toString())
            intent.putExtra("liked_status",movieList?.get(position)!!.liked_status.toString())
            it.context.startActivity(intent)
        }
    }
}

class CustomViewHolder(val v: View) : RecyclerView.ViewHolder(v) {
    val movieicon: ImageView = v.findViewById(R.id.Movie_Icon)
    val moviename: TextView = v.findViewById(R.id.Movie_name)
    val movierating: TextView = v.findViewById(R.id.rating)
    val releaseDate: TextView = v.findViewById(R.id.releaseDate)
    val status: TextView=v.findViewById(R.id.likedStatus)

    fun bind(data: Items?) {
        moviename.text = "Name : " + data?.title
        movierating.text = "Rating : " + data?.vote_average
        releaseDate.text = "Date : " + data?.release_date
        Glide.with(movieicon)
            .load("https://image.tmdb.org/t/p/w185//" + data?.poster_path)
            .into(movieicon)

        if(data?.liked_status==true){
            status.setText("Status : Liked")
        }
        else{
            status.setText("Status : Not Liked")
        }
    }
}