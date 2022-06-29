package com.frigate.appselectmovie.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.frigate.appselectmovie.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var view: MainViewModel
    lateinit var adapter: MovieListAdapter
    lateinit var layoutManager: LinearLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        var offset = 0

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        view = ViewModelProvider(this).get(MainViewModel::class.java)
        setupRecyclerView()

        lifecycleScope.launch {
            fillAdapter(adapter, offset)
        }

        layoutManager = binding.movieList.layoutManager as LinearLayoutManager

        binding.movieList.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItem = layoutManager.itemCount
                val lastVisiblePosition = layoutManager.findLastVisibleItemPosition()
                if (!adapter.isLoading && totalItem <=
                    lastVisiblePosition + 10
                ) {
                    adapter.loadMore?.onLoadMore()
                }
            }
        })
        adapter.loadProcess(object : MovieListAdapter.MyLoader {
            override fun onLoadMore() {
                lifecycleScope.launch {
                    offset += 20
                    fillAdapter(adapter, offset)
                }
            }
        })
    }

    private fun setupRecyclerView() {
        adapter = MovieListAdapter()
        binding.movieList.adapter = adapter
    }

    fun fillAdapter(adapter: MovieListAdapter, offset: Int) {
        lifecycleScope.launch(Dispatchers.Main) {
            view.getMovieListFromVM(offset).observe(this@MainActivity) {
                adapter.fillAdapter(it)
            }
        }
    }
}

