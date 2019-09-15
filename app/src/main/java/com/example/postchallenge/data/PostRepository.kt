package com.example.postchallenge.data

import com.example.postchallenge.data.dao.CommentDao
import com.example.postchallenge.data.dao.PostDao
import com.example.postchallenge.data.dao.UserDao
import com.example.postchallenge.data.model.Comment
import com.example.postchallenge.data.model.Post
import com.example.postchallenge.data.model.User
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class PostRepository : IPostRepository {

  @Inject
  lateinit var postDao: PostDao

  @Inject
  lateinit var userDao: UserDao

  @Inject
  lateinit var commentDao: CommentDao

  override fun getAllPosts(): Single<List<Post>> {
    return postDao.getAllPosts()
  }

  override fun getUser(id: Int): Single<User> {
    return userDao.getUser(id)
  }

  override fun getAllCommentsInPost(id: Int): Single<Int> {
    return commentDao.getAllCommentsInPost(id)
  }

  override fun insertOrUpdatePost(post: Post): Completable {
    return postDao.insertPost(post)
  }

  override fun insertOrUpdateUser(user: User): Completable {
    return userDao.insertUser(user)
  }

  override fun insertOrUpdateComment(comment: Comment): Completable {
    return commentDao.insertComment(comment)
  }

}