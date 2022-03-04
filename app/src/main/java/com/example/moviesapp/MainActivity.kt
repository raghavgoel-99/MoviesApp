package com.example.moviesapp

//import kotlinx.android.synthetic.main.activity_main.*
//import kotlinx.android.synthetic.main.activity_main.*
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import org.json.JSONArray
import java.io.IOException


class MainActivity : AppCompatActivity() {

    private val url="https://imdb-api.com/en/API/Top250Movies/k_1n878jkv"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.Toolbar))
        recyclerView_main.layoutManager = LinearLayoutManager(this)
        fetchJson()
    }

    fun fetchJson() {
        println("fetching")
        val request = Request.Builder()
            .url(url)
            .build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response?.body?.string()
                println(body)
                val gson = GsonBuilder().create()
                val movies = gson.fromJson(body, MoviesList::class.java)
                runOnUiThread {
                    recyclerView_main.adapter = MainAdapter(movies)
                }
            }
            override fun onFailure(call: Call, e: IOException) {
                println("Error:"+e)
            }
        })

    }
}

class MoviesList(val items: List<Items>)
class Items(
    val id: String, val rank: String, val title: String, val fullTitle: String,
    val year: String, val image: String, val crew: String, val imDbRating: String,
    val imDbRatingCount: String
)

//Data format
//    "id": "tt0111161",
//    "rank": "1",
//    "title": "The Shawshank Redemption",
//    "fullTitle": "The Shawshank Redemption (1994)",
//    "year": "1994",
//    "image": "https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_UX128_CR0,3,128,176_AL_.jpg",
//    "crew": "Frank Darabont (dir.), Tim Robbins, Morgan Freeman",
//    "imDbRating": "9.2",
//    "imDbRatingCount": "2551144"