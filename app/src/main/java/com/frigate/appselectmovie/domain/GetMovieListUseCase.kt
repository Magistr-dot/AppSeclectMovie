package com.frigate.appselectmovie.domain

import androidx.lifecycle.LiveData

class GetMovieListUseCase(private val shopListRepo: MovieListRepo) {
    suspend fun getMovieList(offset:Int): LiveData<MutableList<MovieUnit>> {
        return shopListRepo.getMovieList(offset)
    }
}