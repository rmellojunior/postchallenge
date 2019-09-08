package com.example.postchallenge.ui.postlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.postchallenge.R.layout
import com.example.postchallenge.data.model.Post
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class PostListAdapter : RecyclerView.Adapter<ViewHolder>() {

  private val postItemSelectedPublisher: PublishSubject<Post> = PublishSubject.create()

  private var postList = mutableListOf<Post>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
    when (viewType) {
      LIST_TYPE_ITEM -> {
        createPostListItemViewHolder(parent)
      }
      else -> {
        createPostListItemViewHolder(parent)
      }
    }

  private fun createPostListItemViewHolder(parent: ViewGroup): PostListItemViewHolder {
    val view = LayoutInflater
      .from(parent.context)
      .inflate(layout.post_list_item, parent, false)
    return PostListItemViewHolder(view, postItemSelectedPublisher)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    when (holder.itemViewType) {
      LIST_TYPE_ITEM -> {
        val viewHolder = holder as PostListItemViewHolder
        viewHolder.onBind(getCodeForPosition(position), position)
      }
    }
  }

  private fun getCodeForPosition(position: Int): Post =  postList[position]

  override fun getItemViewType(position: Int): Int = LIST_TYPE_ITEM

  override fun getItemCount(): Int = postList.size

  fun updatePostList(postList: List<Post>) {
    this.postList.clear()
    this.postList.addAll(postList)
    notifyDataSetChanged()
  }

  fun postItemSelected(): Observable<Post> = postItemSelectedPublisher

  companion object {
    private const val LIST_TYPE_ITEM = 0
  }
}