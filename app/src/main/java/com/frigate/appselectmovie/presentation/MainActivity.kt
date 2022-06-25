package com.frigate.appselectmovie.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.frigate.appselectmovie.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var view: MainViewModel
    lateinit var adapter: MovieListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {

        var offset = 0
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        view = ViewModelProvider(this).get(MainViewModel::class.java)
        lifecycleScope.launch {
            setupRecyclerView(offset)
        }
        binding.buttonAddShopItem.setOnClickListener {
            offset += 20
            lifecycleScope.launch {
                setupRecyclerView(offset)
            }
        }

    }

    private suspend fun setupRecyclerView(offset: Int) {

        adapter = MovieListAdapter()
        binding.movieList.adapter = adapter

        GlobalScope.launch(Dispatchers.Main) {
            view.getMovieListFromVM(offset).observe(this@MainActivity) {
                adapter.list = it
            }
        }
    }


}

