package com.example.postchallenge.ui.postlist.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.postchallenge.data.model.Post
import io.reactivex.subjects.PublishSubject

class PostListItemViewHolder(
  itemView: View,
  postItemSelectedPublisher: PublishSubject<Post>
) : RecyclerView.ViewHolder(itemView) {

  init {

  }

  fun onBind(post: Post, position: Int) {

  }

}