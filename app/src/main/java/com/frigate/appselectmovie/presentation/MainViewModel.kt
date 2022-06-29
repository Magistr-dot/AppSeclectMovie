package com.frigate.appselectmovie.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.frigate.appselectmovie.data.MovieListRepositoryImpl
import com.frigate.appselectmovie.domain.GetMovieListUseCase
import com.frigate.appselectmovie.domain.MovieUnit


class MainViewModel : ViewModel() {
    private val repository = MovieListRepositoryImpl

    private val getMovieListUseCase = GetMovieListUseCase(repository)

    suspend fun getMovieListFromVM(offset: Int): LiveData<MutableList<MovieUnit>> {
        return getMovieListUseCase.getMovieList(offset)
    }
}