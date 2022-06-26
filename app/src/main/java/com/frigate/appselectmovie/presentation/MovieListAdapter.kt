package com.frigate.appselectmovie.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.frigate.appselectmovie.R
import com.frigate.appselectmovie.databinding.ActivityMainBinding
import com.frigate.appselectmovie.databinding.ItemLayoutBinding
import com.frigate.appselectmovie.domain.MovieUnit

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.MovieUnitViewHolder>() {
    private lateinit var bindding: ItemLayoutBinding

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
        bindding = ItemLayoutBinding.inflate(inflater, parent, false)
        return MovieUnitViewHolder(bindding.root, bindding)
    }

    override fun onBindViewHolder(holder: MovieUnitViewHolder, position: Int) {
        holder.bind(list[position])
        updateAdapter(list)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateAdapter(list: List<MovieUnit>) {

    }
}