package com.example.postchallenge.ui.postlist

import android.os.Bundle
import android.view.View
import com.example.postchallenge.PostApplication
import com.example.postchallenge.R
import com.example.postchallenge.data.model.Post
import com.example.postchallenge.service.PostService
import com.example.postchallenge.ui.base.BaseViewFragment
import com.example.postchallenge.ui.postlist.adapter.PostListAdapter
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_post_list.post_recycler_view

class PostListFragment : BaseViewFragment<PostListPresenter>(), PostListContract.View {

  override val layout: Int = R.layout.fragment_post_list

  override fun getPresenter(): PostListPresenter {
    val app = (activity!!.application as PostApplication)
    val postService = PostService()
    app.netComponent.inject(postService)

    val presenter = PostListPresenter(view = this, postService = postService)
    app.netComponent.inject(presenter)
    return presenter
  }

  private lateinit var adapter: PostListAdapter

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupAdapter(view)
  }

  private fun setupAdapter(view: View) {
    adapter = PostListAdapter(

    )
    post_recycler_view.adapter = adapter
  }

  override fun postItemSelected(): Observable<Post> = adapter.postItemSelected()

  companion object {
    fun newInstance(): PostListFragment = PostListFragment()
  }

}