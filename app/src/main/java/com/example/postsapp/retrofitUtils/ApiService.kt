package com.example.postsapp.retrofitUtils

import com.example.postsapp.model.Post
import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET("posts")
    fun postList() :Call<List<Post>>

    @GET("posts/{id}")
    fun postDetails(@Path("id")  id :Int) :Call<Post>
}