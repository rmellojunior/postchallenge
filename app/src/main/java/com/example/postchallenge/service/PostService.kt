package com.example.postchallenge.service

import com.example.postchallenge.di.SchedulerModule
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Named

class PostService {

  @Inject
  lateinit var postApi: PostApi

  @Inject
  @field:Named(SchedulerModule.IO_SCHEDULER)
  lateinit var ioScheduler: Scheduler

  companion object {
    private const val ENDPOINT_POST_LIST = "posts"
    private const val ENDPOINT_USERS_LIST = "users"
    private const val ENDPOINT_COMMENTS_LIST = "comments"
  }

  fun getPostList(): Single<List<PostItem>> {

    return postApi.getPostList(
      url = ENDPOINT_POST_LIST
    )
      .subscribeOn(ioScheduler)
  }

}