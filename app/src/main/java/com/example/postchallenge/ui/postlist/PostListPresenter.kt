package com.example.postchallenge.ui.postlist

import android.os.Bundle
import android.util.Log
import com.example.postchallenge.di.SchedulerModule
import com.example.postchallenge.service.PostItem
import com.example.postchallenge.service.PostService
import com.example.postchallenge.ui.base.BasePresenter
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject
import javax.inject.Named

class PostListPresenter constructor(
  private val view: PostListContract.View,
  private val navigator: PostListContract.Navigator,
  private val postService: PostService
) : BasePresenter(), PostListContract.Presenter {

  @Inject
  @field:Named(SchedulerModule.VIEW_SCHEDULER)
  lateinit var viewScheduler: Scheduler

  private lateinit var postList: List<PostItem>
  private var hasPostList: Boolean = false

  override fun start(state: Bundle?) {
    super.start(state)
    handleGetPostList()
  }

  override fun handleGetPostList() {
    addOnStartSubscription(
      Observable.just(0)
        .flatMapSingle {
          if (hasPostList) {
            Single.just(postList)
          } else {
            Completable.fromCallable { /*view.prepareLoadingScreen()*/ }
              .andThen(getPostList())
          }
        }
        .observeOn(viewScheduler)
        .subscribeBy(
          onNext = { response: List<PostItem> ->
            hasPostList = true
            if (response.isEmpty()) {
              view.setEmptyState()
            } else {
              view.setPostList(response)
            }
          },
          onError = { error -> Log.e(error.message, "Error") }
        )
    )
  }

  override fun resume() {
    super.resume()
    handlePostSelected()
  }

  override fun handlePostSelected() {
    addOnResumeSubscription(
      view.postItemSelected()
        .observeOn(viewScheduler)
        .subscribeBy(
          onNext = { navigator.navigateToPostDetail(it) },
          onError = { error -> Log.e(error.message, "ERROR")}
        )
    )
  }

  private fun getPostList(): Single<List<PostItem>> = postService.getPostList()

}