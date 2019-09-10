package com.example.postchallenge.ui.postlist.detail

import android.os.Bundle
import android.util.Log
import com.example.postchallenge.di.SchedulerModule
import com.example.postchallenge.service.Comment
import com.example.postchallenge.service.PostItem
import com.example.postchallenge.service.PostService
import com.example.postchallenge.service.User
import com.example.postchallenge.ui.base.BasePresenter
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject
import javax.inject.Named

class PostDetailPresenter constructor(
  private val view: PostDetailContract.View,
  private val postService: PostService
) : BasePresenter(), PostDetailContract.Presenter {

  @Inject
  @field:Named(SchedulerModule.VIEW_SCHEDULER)
  lateinit var viewScheduler: Scheduler

  private lateinit var postItem: PostItem

  override fun setInitialState(state: Bundle?) {
    super.setInitialState(state)
    state?.let {
      if(state.containsKey(PostDetailContract.EXTRA_POST_ITEM))
        postItem = state.getParcelable(PostDetailContract.EXTRA_POST_ITEM)!!
    }
  }

  override fun resume() {
    super.resume()
    handleGetPostDetail()
  }

  override fun handleGetPostDetail() {
    addOnResumeSubscription(
      Single.zip(
        getUsers(),
        getComments(),
        BiFunction<List<User>, List<Comment>, Pair<String, Int>> { users, comments ->
          Pair(users.find { it.id == postItem.userId }!!.name, comments.filter { it.postId == postItem.id }.size)
        }
      )
        .observeOn(viewScheduler)
        .subscribeBy(
          onSuccess = { (name, numberOfComments) ->
            view.setName(name)
            view.setNumberOfComments(numberOfComments)
            view.setDescription(postItem.body)
          },
          onError = { error ->
            Log.e(error.message, "ERROR")
          }
        )
    )
  }

  private fun getUsers(): Single<List<User>> = postService.getUsers()

  private fun getComments(): Single<List<Comment>> = postService.getComments()
}