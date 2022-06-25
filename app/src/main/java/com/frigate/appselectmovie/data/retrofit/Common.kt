package com.frigate.appselectmovie.data.retrofit

object Common {
    private const val BASE_URL = "https://api.nytimes.com"
    val retrofitService: MovieReviewsApiService
        get() = RetrofitClient.getClient(BASE_URL).create(MovieReviewsApiService::class.java)
}