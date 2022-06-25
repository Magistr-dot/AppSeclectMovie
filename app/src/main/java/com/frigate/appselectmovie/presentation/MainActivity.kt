package com.frigate.appselectmovie.presentation

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.frigate.appselectmovie.R
import com.frigate.appselectmovie.data.retrofit.Common
import com.frigate.appselectmovie.data.retrofit.Common.retrofitService
import com.frigate.appselectmovie.data.retrofit.MovieReviewsApiService
import com.frigate.appselectmovie.databinding.ActivityMainBinding
import com.frigate.appselectmovie.domain.MovieUnit
import com.frigate.appselectmovie.domain.json.ParseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.awaitResponse
import javax.xml.bind.JAXBElement

var thread = Thread {
    try {
        Common.retrofitService.allReviews("PhHi4horYcyh3nMJMyMlRtu5A9560kom", 1).execute()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var view: MainViewModel
    lateinit var adapter: MovieListAdapter

    lateinit var mService: MovieReviewsApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val th = this


        view = ViewModelProvider(th).get(MainViewModel::class.java)
        lifecycleScope.launch {
            setupRecyclerView(th)
        }
    }

    private suspend fun setupRecyclerView(th: LifecycleOwner) {

        val rvMovieList = findViewById<RecyclerView>(R.id.shop_list)
        adapter = MovieListAdapter()
        rvMovieList.adapter = adapter

        GlobalScope.launch(Dispatchers.Main) {
//            adapter.list = listOf(MovieUnit("2211113","asd"))
            view.getMovieListFromVM().observe(th) {
                adapter.list = it
            }
        }



    }

    private suspend fun getAllMovieList() {
        val response =
            retrofitService.allReviews("PhHi4horYcyh3nMJMyMlRtu5A9560kom", 1).awaitResponse()
        GlobalScope.launch(Dispatchers.IO) {
            if (response.isSuccessful) {
                val data = response.body()
                data?.results?.get(0)?.let { Log.d("MainA", it.display_title) }
                Log.d("fad", data!!.results[1].display_title)
            }
        }

    }

}

