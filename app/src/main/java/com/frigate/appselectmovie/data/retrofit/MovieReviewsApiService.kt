package com.frigate.appselectmovie.data.retrofit

import com.frigate.appselectmovie.domain.json.ParseResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//https://api.nytimes.com/svc/movies/v2/reviews/all.json?api-key=PhHi4horYcyh3nMJMyMlRtu5A9560kom&offset=3990
interface MovieReviewsApiService {
    @GET("/svc/movies/v2/reviews/all.json")
    fun allReviews(
        @Query("api-key")
        key: String,
        @Query("offset")
        offset: Int
    ): Call<ParseResult>
}