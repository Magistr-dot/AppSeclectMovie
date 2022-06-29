package com.frigate.appselectmovie.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.frigate.appselectmovie.databinding.ItemLayoutBinding
import com.frigate.appselectmovie.domain.MovieUnit

class MovieListAdapter :
    RecyclerView.Adapter<MovieListAdapter.MovieUnitViewHolder>() {

    private lateinit var binding: ItemLayoutBinding
    private var listMovies = mutableListOf<MovieUnit>()
    set(value) {
        val callback = MovieListDiff(listMovies,value)
        val diffResult = DiffUtil.calculateDiff(callback)
        diffResult.dispatchUpdatesTo(this)
        field = value
    }
    var isLoading = false
    var loadMore: MyLoader? = null



    class MovieUnitViewHolder(private val view: View, private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(view) {
        fun bind(movieUnit: MovieUnit) {
            binding.title.text = movieUnit.name
            binding.description.text = movieUnit.disk
            Glide.with(view.context).load(movieUnit.url).centerCrop().into(binding.imageMovie)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieUnitViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemLayoutBinding.inflate(inflater, parent, false)
        return MovieUnitViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: MovieUnitViewHolder, position: Int) {
        holder.bind(listMovies[position])
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }

    fun loadProcess(loadMore: MyLoader?) {
        this.loadMore = loadMore
    }

    private fun startLoading() {
        isLoading = true
    }

    private fun endLoaded() {
        isLoading = false
    }

    interface MyLoader {
        fun onLoadMore()
    }

    fun fillAdapter(listFromForm: MutableList<MovieUnit>) {
        startLoading()
        listMovies = listFromForm
        endLoaded()
    }
}