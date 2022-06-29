package com.frigate.appselectmovie.presentation

import androidx.recyclerview.widget.DiffUtil
import com.frigate.appselectmovie.domain.MovieUnit

class MovieListDiff(
    private val oldList: MutableList<MovieUnit>,
    private val newList: MutableList<MovieUnit>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem == newItem
    }
}