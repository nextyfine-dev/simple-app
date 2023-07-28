package com.shubhadeep.app.services

import com.shubhadeep.app.types.FeedItem
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("photos?_start=0&_limit=10")
    suspend fun getFeeds(): List<FeedItem>
}

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://jsonplaceholder.typicode.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val apiService = retrofit.create(ApiService::class.java)
