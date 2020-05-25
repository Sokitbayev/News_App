package com.example.newsapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsapiEndpoints {

    @GET("/v2/sources?")
    fun getNews(@Query("api_key") key: String): Call<NewsApiData>
}