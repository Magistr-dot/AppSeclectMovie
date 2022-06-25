package com.frigate.appselectmovie.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.frigate.appselectmovie.data.MovieListRepositoryImpl
import com.frigate.appselectmovie.domain.GetMovieListUseCase
import com.frigate.appselectmovie.domain.MovieUnit
import kotlin.coroutines.suspendCoroutine


class MainViewModel : ViewModel() {
    private val repository = MovieListRepositoryImpl

    private val getMovieListUseCase = GetMovieListUseCase(repository)

    suspend fun getMovieListFromVM() : LiveData<List<MovieUnit>>{
        return getMovieListUseCase.getMovieList()
    }


}