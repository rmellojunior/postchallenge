package com.example.postchallenge

import com.example.postchallenge.ui.postlist.PostListContract.View
import com.example.postchallenge.ui.postlist.PostListPresenter
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import org.junit.Test

class PostListPresenterTest {

//  private val publisherPostItemSelected = PublishSubject.create<Any>()
//
//  private val view: View = mockk(relaxUnitFun = true) {
//    every { setEmptyState() } returns
//    every { setPostList() } returns
//    every { postItemSelected() } returns publisherPostItemSelected
//  }
//
//  private val locationService: LocationService = mockk {
//    every { lastLocationOrError() } returns Single.just(GpsLocation.EMPTY)
//  }
//
//  private val presenter: PostListPresenter = spyk(
//    PostListPresenter(
//      view = view,
//      navigator = mockk(),
//      postService: PostService
//    ).apply {
//      viewScheduler = Schedulers.trampoline()
//    }
//  )
//
//  @Test
//  fun `presenter start`() {
//    // should get all the debug information{
//    presenter.start()
//    verify(exactly = 1) { presenter.handleGetPostList() }
//
//  }

}