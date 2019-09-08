package com.example.postchallenge.service

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface PostApi {

  @GET
  fun getPostList(
    @Url url: String
  ): Single<List<PostItem>>

}