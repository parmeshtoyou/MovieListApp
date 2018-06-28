package com.example.parmeshmahore.movielistdemo.rest

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
class ApiClient {
    val BASE_URL = "http://api.themoviedb.org/3/"
    var retrofit : Retrofit? = null

    fun getClient() : Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create()).build()
        }
        return retrofit
    }
}