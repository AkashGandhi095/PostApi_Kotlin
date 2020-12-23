package com.example.postsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.postsapp.R
import com.example.postsapp.model.Post
import com.google.android.material.textview.MaterialTextView

class PostAdapter (private var postList :List<Post>) : RecyclerView.Adapter<PostAdapter.PostHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapter.PostHolder =
            PostHolder(LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.post_row , parent , false))


    override fun onBindViewHolder(holder: PostAdapter.PostHolder, position: Int) {
        val post = postList[position]
        holder.bindData(post)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    fun setPosts(postList: List<Post>) {
        this.postList = postList
    }

    inner class PostHolder(itemView :View) :RecyclerView.ViewHolder(itemView) {

        private val titleText = itemView.findViewById<MaterialTextView>(R.id.tv_title)
        private val bodyText = itemView.findViewById<MaterialTextView>(R.id.tv_body)

        fun bindData(post :Post) {

            titleText.text = post.title
            bodyText.text = post.body
        }

    }
}