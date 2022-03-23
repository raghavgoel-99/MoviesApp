package com.example.moviesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.R
import com.example.moviesapp.adapter.GenreAdapter
import com.example.moviesapp.adapter.MainAdapter
import com.example.moviesapp.viewmodel.GenreViewModel
import com.example.moviesapp.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_genre.*
import kotlinx.android.synthetic.main.activity_main.*

class GenreActivity : AppCompatActivity() {

    lateinit var recyclergenreAdapter: GenreAdapter
    lateinit var viewModel: GenreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genre)

        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {

                return GenreViewModel(application) as T
            }
        }).get(GenreViewModel::class.java)

        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
        recyclerView_genre.layoutManager = LinearLayoutManager(this)
        recyclergenreAdapter = GenreAdapter()
        recyclerView_genre.adapter = recyclergenreAdapter
    }

    private fun initViewModel() {
        viewModel.makeApiCallGenre()
        viewModel.getlivegenredata().observe(this, {
            if (it != null) {
                recyclergenreAdapter.setGenreList(it)
                recyclergenreAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "No Internet Access :", Toast.LENGTH_LONG).show()
            }
        })
    }
}