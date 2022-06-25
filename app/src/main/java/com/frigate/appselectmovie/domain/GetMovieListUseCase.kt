package com.frigate.appselectmovie.domain

import androidx.lifecycle.LiveData

class GetMovieListUseCase(private val shopListRepo: MovieListRepo) {
    suspend fun getMovieList(): LiveData<List<MovieUnit>> {
        return shopListRepo.getMovieList()
    }
}