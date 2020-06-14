package com.alien.brainean.coroutinesandflows.data

import com.alien.brainean.coroutinesandflows.models.Post
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


interface NetworkService {

    @GET("posts")
    suspend fun getPosts(): List<Post>
}


val apiService by lazy {
    Retrofit.Builder()
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
            )
        )
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .build()
        .create(NetworkService::class.java)
}