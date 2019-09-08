package com.example.postchallenge.ui.postlist

import android.os.Bundle
import android.util.Log
import com.example.postchallenge.di.SchedulerModule
import com.example.postchallenge.service.PostItem
import com.example.postchallenge.service.PostService
import com.example.postchallenge.ui.base.BasePresenter
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject
import javax.inject.Named

class PostListPresenter constructor(
  private val view: PostListContract.View,
  private val postService: PostService
) : BasePresenter(), PostListContract.Presenter {

  @Inject
  @field:Named(SchedulerModule.VIEW_SCHEDULER)
  lateinit var viewScheduler: Scheduler

  override fun saveState(): Bundle? = null

  override fun start(state: Bundle?) {
    super.start(state)
    handleGetPostList()
  }

  override fun handleGetPostList() {
    addOnStartSubscription(
      Observable.just(0)
        .flatMapSingle { user ->
          getPostList()
        }
        .observeOn(viewScheduler)
        .subscribeBy(
          onNext = { response ->
            Log.d("","")
          },
          onError = { error ->
            Log.e(error.message, "Error")
          }
        )
    )
  }

  private fun getPostList(): Single<List<PostItem>> = postService.getPostList()

}