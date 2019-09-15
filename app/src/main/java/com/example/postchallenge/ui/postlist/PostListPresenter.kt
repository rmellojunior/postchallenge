package com.example.postchallenge.ui.postlist

import android.util.Log
import com.example.postchallenge.data.PostRepository
import com.example.postchallenge.di.SchedulerModule
import com.example.postchallenge.data.model.Post
import com.example.postchallenge.service.PostService
import com.example.postchallenge.ui.base.BasePresenter
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject
import javax.inject.Named

class PostListPresenter constructor(
  private val view: PostListContract.View,
  private val navigator: PostListContract.Navigator,
  private val postService: PostService,
  private val repository: PostRepository
) : BasePresenter(), PostListContract.Presenter {

  @Inject
  @field:Named(SchedulerModule.VIEW_SCHEDULER)
  lateinit var viewScheduler: Scheduler

  override fun start() {
    super.start()
    handleGetPostList()
  }

  override fun handleGetPostList() {
    addOnStartSubscription(
      view.isInternetOn()
        .flatMapSingle { hasInternet ->
          if (!hasInternet) {
            Completable.fromCallable { /*view.prepareLoadingScreen()*/ }
              .andThen(repository.getAllPosts())
          } else {
            Completable.fromCallable { /*view.prepareLoadingScreen()*/ }
              .andThen(getPostList())
          }
        }
        .observeOn(viewScheduler)
        .subscribeBy(
          onNext = { response: List<Post> ->
            if (response.isEmpty()) {
              view.setEmptyState()
            } else {
              response.forEach { repository.insertOrUpdatePost(it) }
              view.setPostList(response)
            }
          },
          onError = { error ->
            Log.e(error.message, "Error")
          }
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

  private fun getPostList(): Single<List<Post>> = postService.getPostList()

}