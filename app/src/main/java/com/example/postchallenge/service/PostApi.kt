package com.example.postchallenge.service

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface PostApi {

  @GET
  fun getPostList(
    @Url url: String
  ): Single<List<PostItem>>

  @GET
  fun getUsers(
    @Url url: String
  ): Single<List<User>>

  @GET
  fun getComments(
    @Url url: String
  ): Single<List<Comment>>

}