package com.example.postchallenge.ui.postlist

import com.example.postchallenge.data.model.Post
import com.example.postchallenge.ui.base.BaseContract
import io.reactivex.Observable

interface PostListContract {

  interface View : BaseContract.View {
    fun hideProgressBar()
    fun showProgressBar()
    fun showParent()
    fun hideParent()
    fun setPostList(postList: List<Post>)
    fun setEmptyState()
    fun postItemSelected(): Observable<Post>
  }

  interface Presenter : BaseContract.Presenter {
    fun handleGetPostList()
    fun handlePostSelected()
  }

  interface Navigator {
    fun navigateToPostDetail(post: Post)
  }
}