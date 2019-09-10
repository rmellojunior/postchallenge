package com.example.postchallenge.ui.postlist.adapter

import android.util.Log
import android.view.View
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.postchallenge.R
import com.example.postchallenge.service.PostItem
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.post_list_item.view.post_list_item_body
import kotlinx.android.synthetic.main.post_list_item.view.post_list_item_parent
import kotlinx.android.synthetic.main.post_list_item.view.post_list_item_title

class PostListItemViewHolder constructor(
  itemView: View,
  postItemSelectedPublisher: PublishSubject<PostItem>,
  viewDetachEvent: Observable<Any>,
  viewScheduler: Scheduler
) : RecyclerView.ViewHolder(itemView) {

  private lateinit var currentPostItem: PostItem

  init {
    RxView.clicks(itemView.post_list_item_parent)
      .takeUntil(viewDetachEvent)
      .observeOn(viewScheduler)
      .retry { error ->
        Log.e(error.message, "ERROR")
        true
      }
      .subscribeBy(
        onNext = { postItemSelectedPublisher.onNext(currentPostItem) },
        onError = { error ->         Log.e(error.message, "ERROR") }
      )
  }

  fun onBind(post: PostItem) {
    this.currentPostItem = post
    itemView.post_list_item_title.text = post.title
    itemView.post_list_item_body.text = post.body
  }

  companion object {
    @LayoutRes const val LAYOUT = R.layout.post_list_item
  }
}