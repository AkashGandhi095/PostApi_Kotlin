package com.example.postsapp.retrofitUtils

import com.example.postsapp.model.Post
import retrofit2.Call
import retrofit2.http.GET


interface ApiService {

    @GET("posts")
    fun postList() :Call<List<Post>>
}