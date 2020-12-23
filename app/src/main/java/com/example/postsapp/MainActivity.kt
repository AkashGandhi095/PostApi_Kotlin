package com.example.postsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.postsapp.adapter.PostAdapter
import com.example.postsapp.model.Post
import com.example.postsapp.retrofitUtils.ApiService
import com.example.postsapp.retrofitUtils.RetrofitService
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivityClass"
    }

    private lateinit var postRecycleView :RecyclerView
    private lateinit var postAdapter :PostAdapter
    private lateinit var postList: List<Post>
    private lateinit var loadingDialog :AlertDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        fetchPosts()




    }

    private fun initViews() {
        postRecycleView = findViewById(R.id.rv_posts)
        postList = listOf()
        postAdapter = PostAdapter(postList)
        postRecycleView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = postAdapter
        }

        loadingDialog = MaterialAlertDialogBuilder(this)
                .setView(R.layout.loading_oview).create()


        loadingDialog.apply {
            setCancelable(false)
            setCanceledOnTouchOutside(false)
        }

    }

    private fun fetchPosts() {
        Log.d(RetrofitService.TAG, "fetchPosts: getPostApiService hashCode : ${RetrofitService.postService.hashCode()}")
        loadingDialog.show()
        RetrofitService.postService
            .postList().enqueue(object : Callback<List<Post>> {
                    override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                        //Log.d(TAG, "onResponse: posts : ${response.body()}")
                        if (response.isSuccessful) {
                            bindPostToList(response.body())
                        } else {
                            Log.d(TAG, "onResponse: errorResponse : ${response.errorBody()}")
                        }

                        loadingDialog.hide()
                    }

                    override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                        Log.d(TAG, "onFailure: error : ${t.localizedMessage}")
                        loadingDialog.hide()
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






}