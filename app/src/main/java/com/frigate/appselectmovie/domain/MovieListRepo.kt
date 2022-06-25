package com.frigate.appselectmovie.domain

import androidx.lifecycle.LiveData

interface MovieListRepo {
    suspend fun getMovieList(): LiveData<List<MovieUnit>>
}