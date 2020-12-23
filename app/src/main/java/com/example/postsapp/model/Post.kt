package com.example.postsapp.model

import com.google.gson.annotations.SerializedName

data class Post (
    @SerializedName("userId")
    val _userId :Int ,

    @SerializedName("id")
    val _id :Int ,

    @SerializedName("title")
    val title :String ,

    @SerializedName("body")
    var body :String
)