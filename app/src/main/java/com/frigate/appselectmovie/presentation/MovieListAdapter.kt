package com.frigate.appselectmovie.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.frigate.appselectmovie.databinding.ItemLayoutBinding
import com.frigate.appselectmovie.domain.MovieUnit

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.MovieUnitViewHolder>() {
    private lateinit var binding: ItemLayoutBinding
    var isLoading = false
    var loadMore : MyLoader? = null



    var list = mutableListOf<MovieUnit>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

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
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateAdapter(list: MutableList<MovieUnit>) {
        list.clear()
        list.addAll(list)
        notifyDataSetChanged()
    }



    fun loadProcess(loadMore: MyLoader?) {
        this.loadMore = loadMore
    }

    fun startLoading() {
        isLoading = true
    }

    fun setLoaded() {
        isLoading = false
    }

    interface MyLoader {
        fun onLoadMore()
    }
    fun fillAdapter(){
        if (list.isNotEmpty()) updateAdapter(list)
    }
}