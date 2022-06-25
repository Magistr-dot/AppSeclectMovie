package com.frigate.appselectmovie.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.frigate.appselectmovie.R
import com.frigate.appselectmovie.domain.MovieUnit

class MovieListAdapter() : RecyclerView.Adapter<MovieListAdapter.MovieUnitViewHolder>() {
    var list = listOf<MovieUnit>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    class MovieUnitViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(movieUnit: MovieUnit){
            val title = view.findViewById<TextView>(R.id.title)
            val imageView = view.findViewById<ImageView>(R.id.imageMovie)
            val description = view.findViewById<TextView>(R.id.description)

            title.text = movieUnit.name
            description.text = movieUnit.disk

            Glide.with(view.context).load("https://openai.com/content/images/2021/01/2x-no-mark-1.jpg").centerCrop().into(imageView)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieUnitViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MovieUnitViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieUnitViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}