package com.example.postchallenge.ui.postlist

import com.example.postchallenge.R
import com.example.postchallenge.service.PostItem
import com.example.postchallenge.ui.base.BaseActivity
import com.example.postchallenge.ui.postlist.detail.PostDetailFragment
import com.example.postchallenge.utils.extensions.replaceFragment

class PostListNavigator(
  private val parentActivity: BaseActivity
) : PostListContract.Navigator {

  override fun navigateToPostDetail(postItem: PostItem) {
    parentActivity.replaceFragment(
      fragment = PostDetailFragment.newInstance(postItem),
      containerViewId = R.id.nav_host_fragment,
      tag = PostDetailFragment::class.java.name
    )
  }

}