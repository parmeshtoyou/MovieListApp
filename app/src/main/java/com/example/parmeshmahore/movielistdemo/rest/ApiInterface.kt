package com.example.parmeshmahore.movielistdemo.rest

import retrofit2.http.GET
import com.example.parmeshmahore.movielistdemo.model.MovieResponse
import io.reactivex.Single
import retrofit2.http.Query


interface ApiInterface {

    @GET("movie/top_rated")
    fun getTopMoviesList(@Query("api_key") apiKey: String): Single<MovieResponse>
}