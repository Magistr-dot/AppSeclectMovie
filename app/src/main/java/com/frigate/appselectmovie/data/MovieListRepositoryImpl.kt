package com.frigate.appselectmovie.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.frigate.appselectmovie.data.retrofit.Common
import com.frigate.appselectmovie.domain.MovieListRepo
import com.frigate.appselectmovie.domain.MovieUnit
import com.frigate.appselectmovie.domain.json.ParseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

object MovieListRepositoryImpl : MovieListRepo {
    private const val KEY = "PhHi4horYcyh3nMJMyMlRtu5A9560kom"

    private val movieListLD = MutableLiveData<MutableList<MovieUnit>>()
    private val movieList = mutableListOf<MovieUnit>()

    override suspend fun getMovieList(offset:Int): LiveData<MutableList<MovieUnit>> {
        val response = Common.retrofitService.allReviews(KEY, offset).awaitResponse()
        GlobalScope.launch(Dispatchers.IO) {
            if (response.isSuccessful) {
                val data = response.body()
                mapper(data!!)
                update()
            }
        }
        return movieListLD
    }

    private fun update() {
        movieListLD.postValue(movieList)
    }

    private fun mapper(data: ParseResult) {
        data.results.forEach {
            movieList.add(MovieUnit(it.display_title, it.summary_short, it.multimedia.src))
        }
    }

}