package com.example.postsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.postsapp.adapter.OnAdapterClick
import com.example.postsapp.adapter.PostAdapter
import com.example.postsapp.appUtils.LoadingDialog
import com.example.postsapp.model.Post
import com.example.postsapp.retrofitUtils.ApiService
import com.example.postsapp.retrofitUtils.RetrofitService
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() , OnAdapterClick {

    companion object {
        private const val TAG = "MainActivityClass"
    }

    private lateinit var postRecycleView :RecyclerView
    private lateinit var postAdapter :PostAdapter
    private lateinit var postList: List<Post>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        fetchPosts()




    }

    private fun initViews() {

        postRecycleView = findViewById(R.id.rv_posts)
        postList = listOf()
        postAdapter = PostAdapter(postList , this)
        postRecycleView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = postAdapter
        }

        LoadingDialog.createLoadingDialog(this)


    }

    private fun fetchPosts() {

        LoadingDialog.showLoadingDialog()
        RetrofitService.postService
            .postList().enqueue(object : Callback<List<Post>> {
                    override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                        //Log.d(TAG, "onResponse: posts : ${response.body()}")
                        if (response.isSuccessful) {
                            bindPostToList(response.body())
                        } else {
                            Log.d(TAG, "onResponse: errorResponse : ${response.errorBody()}")
                        }

                        LoadingDialog.hideLoadingDialog()
                    }

                    override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                        Log.d(TAG, "onFailure: error : ${t.localizedMessage}")

                        LoadingDialog.hideLoadingDialog()
                    }
                })
    }

    private fun bindPostToList(postList: List<Post>?) {
        if (postList != null) {
            this.postList = postList
            this.postList.map {
                post ->
                post.body = post.body.replace('\n' , ' ' )
            }
            postAdapter.setPosts(postList)
            postAdapter.notifyDataSetChanged()
        }
    }

    override fun onClick(id :Int) {
        val intent = Intent(this , DetailActivity::class.java)
        intent.apply {
            putExtra("_ID" , id)
            startActivity(this)
        }
    }






}