package com.example.postchallenge.ui.postlist.detail

import android.os.Bundle
import com.example.postchallenge.PostApplication
import com.example.postchallenge.R
import com.example.postchallenge.service.PostItem
import com.example.postchallenge.service.PostService
import com.example.postchallenge.ui.base.BaseViewFragment
import kotlinx.android.synthetic.main.fragment_post_detail.post_detail_item_author
import kotlinx.android.synthetic.main.fragment_post_detail.post_detail_item_description
import kotlinx.android.synthetic.main.fragment_post_detail.post_detail_item_number_of_comments

class PostDetailFragment : BaseViewFragment<PostDetailPresenter>(), PostDetailContract.View {

  override val layout: Int = LAYOUT

  override fun getPresenter(): PostDetailPresenter {
    val parentActivity = activity!!
    val app = (parentActivity.application as PostApplication)

    val postService = PostService()
    app.netComponent.inject(postService)

    val presenter = PostDetailPresenter(
      view = this,
      postService = postService
    )
    app.netComponent.inject(presenter)
    return presenter
  }

  override fun setName(name: String) {
    post_detail_item_author.text = name
  }

  override fun setNumberOfComments(number: Int) {
    post_detail_item_number_of_comments.text = number.toString()
  }

  override fun setDescription(description: String) {
    post_detail_item_description.text = description
  }

  companion object {
    private const val LAYOUT = R.layout.fragment_post_detail

    fun newInstance(postItem: PostItem): PostDetailFragment {
      val fragment = PostDetailFragment()
      val bundle = Bundle()
      bundle.putParcelable(PostDetailContract.EXTRA_POST_ITEM, postItem)
      fragment.arguments = bundle
      return fragment
    }
  }
}