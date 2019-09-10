package com.example.postchallenge.ui.postlist

import com.example.postchallenge.service.PostItem
import com.example.postchallenge.ui.base.BaseContract
import io.reactivex.Observable

interface PostListContract {

  interface View : BaseContract.View {
    fun setPostList(postList: List<PostItem>)
    fun setEmptyState()
    fun postItemSelected(): Observable<PostItem>
  }

  interface Presenter : BaseContract.Presenter {
    fun handleGetPostList()
    fun handlePostSelected()
  }

  interface Navigator {
    fun navigateToPostDetail(postItem: PostItem)
  }
}