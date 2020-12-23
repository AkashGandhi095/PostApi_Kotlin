package com.example.postsapp.retrofitUtils

import android.util.Log
import android.view.Gravity.apply
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    const val TAG = "RetrofitServiceClass"
    private const val URL = "https://jsonplaceholder.typicode.com/"


    private val retrofitInstance :Retrofit by lazy {
        Retrofit.Builder().apply {
            baseUrl(URL)
            addConverterFactory(GsonConverterFactory.create())
        }.build()
    }


     val postService :ApiService by lazy {
        Log.d(TAG, "getPostApiService: retrofitInstance hashCode : ${retrofitInstance.hashCode()}")
        retrofitInstance.create(ApiService::class.java)
    }


}