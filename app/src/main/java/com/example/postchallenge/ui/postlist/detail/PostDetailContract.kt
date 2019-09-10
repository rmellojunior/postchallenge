package com.example.postchallenge.ui.postlist.detail

class PostDetailContract {

  interface View {
    fun setName(name: String)
    fun setNumberOfComments(number: Int)
    fun setDescription(description: String)
  }

  interface Presenter {
    fun handleGetPostDetail()
  }

  interface Navigator

  companion object {
    const val EXTRA_POST_ITEM = "extra_post_item"
  }

}