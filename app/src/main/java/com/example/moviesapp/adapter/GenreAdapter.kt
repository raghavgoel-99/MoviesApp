package com.example.moviesapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.R
import com.example.moviesapp.database.Genres
import com.example.moviesapp.database.Items
import com.example.moviesapp.ui.MainActivity

class GenreAdapter : RecyclerView.Adapter<CustomViewHolder2>() {
    private var genreList: List<Genres>? = null

    override fun getItemCount(): Int {
        if (genreList == null)
            return 0
        else
            return genreList?.size!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder2 {
        val layoutInflater = LayoutInflater.from(parent.context)
        val currentgenre = layoutInflater.inflate(R.layout.genre_model, parent, false)
        return CustomViewHolder2(currentgenre)
    }

    override fun onBindViewHolder(holder: CustomViewHolder2, position: Int) {
        holder.bind(genreList?.get(position)!!)
        holder.v.setOnClickListener {
            val intent = Intent(it.context, MainActivity::class.java)
            intent.putExtra("id", genreList?.get(position)!!.id.toString())
            intent.putExtra("name", genreList?.get(position)!!.name.toString())
            it.context.startActivity(intent)
        }
    }

    fun setGenreList(genresList: List<Genres>) {
        this.genreList = genresList
    }
}

class CustomViewHolder2(val v: View) : RecyclerView.ViewHolder(v) {
    val genrename: TextView = v.findViewById(R.id.genre_name)
    fun bind(data: Genres?) {
        genrename.text = data?.name
    }

}