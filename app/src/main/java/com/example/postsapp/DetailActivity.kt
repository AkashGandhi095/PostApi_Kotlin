package com.example.postsapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.postsapp.appUtils.LoadingDialog
import com.example.postsapp.model.Post
import com.example.postsapp.retrofitUtils.RetrofitService
import com.google.android.material.textview.MaterialTextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class DetailActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "DetailActivityScreen"
    }

    private lateinit var idTextV :MaterialTextView
    private lateinit var userIdTextV: MaterialTextView
    private lateinit var titleTextV :MaterialTextView
    private lateinit var bodyTextV: MaterialTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val dataIntent : Bundle? = intent.extras

        initViews()

        dataIntent?.let {

            val id = it.getInt("_ID" , -1)
            fetchPostDetails(id)

        }


    }

    private fun fetchPostDetails(id :Int) {

        LoadingDialog.showLoadingDialog()
        RetrofitService.postService.postDetails(id).enqueue(object : Callback<Post> {

            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful) {
                    Log.d(TAG, "onResponse: ${response.body().toString()}")
                    showData(response.body())
                } else {
                    Log.d(TAG, "onResponse: Error : ${response.errorBody()}")
                }

                LoadingDialog.hideLoadingDialog()
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.localizedMessage}")
                LoadingDialog.hideLoadingDialog()
            }

        })
    }

    private fun initViews() {

        idTextV = findViewById(R.id.idTextView)
        userIdTextV = findViewById(R.id.userIdTextView)
        titleTextV = findViewById(R.id.titleTextView)
        bodyTextV = findViewById(R.id.bodyTextView)

        LoadingDialog.createLoadingDialog(this)

    }

    @SuppressLint("SetTextI18n")
    private fun showData(post: Post?) {
        post?.let {

            idTextV.text = "Id : ${it._id}"
            userIdTextV.text = "UserId : ${it._userId}"

            titleTextV.text = it.title
            bodyTextV.text = it.body
        }
    }

}