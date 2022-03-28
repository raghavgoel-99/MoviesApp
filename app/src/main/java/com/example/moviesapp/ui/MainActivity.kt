package com.example.moviesapp.ui

import android.os.Bundle
import android.widget.AbsListView
import android.widget.Toast
import android.widget.Toolbar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.R
import com.example.moviesapp.adapter.MainAdapter
import com.example.moviesapp.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var recyclermainAdapter: MainAdapter
    lateinit var viewModel: MainActivityViewModel
    var genreId: Int? = null
    var genreName: String? = null
    var isLoading = false
    var isLastPage = false
    var isScrolling = false
    lateinit var mActionBarToolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //fetch data from Intent
        genreId = intent.getStringExtra("id")!!?.toInt()
        genreName = intent.getStringExtra("name")

        //Setting toolbar
        mActionBarToolbar = findViewById(R.id.Toolbar)
        setSupportActionBar(mActionBarToolbar)
        if(genreName.equals("Documentary"))
        {
            getSupportActionBar()?.setTitle(genreName)
        }
        else
        {
            getSupportActionBar()?.setTitle(genreName + " Movies")
        }
        //ViewModel Instance
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainActivityViewModel(application) as T
            }
        }).get(MainActivityViewModel::class.java)

        viewModel.DeletAll()
        initRecyclerView()
        initViewModel()
    }

    override fun onResume() {
        super.onResume()
       viewModel.refresh()
    }
    val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val TotalItemCount = layoutManager.itemCount

            val isNotLoadingNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= TotalItemCount
            val isNotAtBegin = firstVisibleItemPosition >= 0

            if (isNotLoadingNotLastPage && isAtLastItem && isNotAtBegin && isScrolling) {
                viewModel.makeApiCall(genreId!!)
                isScrolling = false
            }
        }
    }

    private fun initRecyclerView() {
        recyclerView_main.layoutManager = LinearLayoutManager(this)
        recyclermainAdapter = MainAdapter()
        recyclerView_main.addOnScrollListener(scrollListener)
        recyclerView_main.adapter = recyclermainAdapter
    }

    private fun initViewModel() {
        viewModel.makeApiCall(genreId!!)
        viewModel.getlivedata().observe(this, {
            if (it != null) {
                recyclermainAdapter.setMovieList(it)
                recyclermainAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "No Internet Access :", Toast.LENGTH_LONG).show()
            }
        })
    }

}
