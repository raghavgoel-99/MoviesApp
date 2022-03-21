package com.example.moviesapp.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.R
import com.example.moviesapp.adapter.MainAdapter
import com.example.moviesapp.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var recyclermainAdapter: MainAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
        recyclerView_main.layoutManager = LinearLayoutManager(this)
        recyclermainAdapter = MainAdapter()
        recyclerView_main.adapter = recyclermainAdapter
    }

    private fun initViewModel() {
        val ViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {

                return MainActivityViewModel(application) as T
            }

        }).get(MainActivityViewModel::class.java)
        ViewModel.getlivedata().observe(this, {
            if (it != null) {
                recyclermainAdapter.setMovieList(it)
                recyclermainAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "No Internet Access :", Toast.LENGTH_LONG).show()
            }
        })

        ViewModel.makeApiCall()
    }

}
