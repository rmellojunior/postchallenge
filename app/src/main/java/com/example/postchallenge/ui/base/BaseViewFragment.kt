package com.example.postchallenge.ui.base

import android.os.Bundle

abstract class BaseViewFragment<T : BaseContract.Presenter> : BaseFragment(), BaseContract.View {

  abstract fun getPresenter(): T

  override fun onViewStateRestored(savedInstanceState: Bundle?) {
    super.onViewStateRestored(savedInstanceState)
    getPresenter().start(savedInstanceState)
  }

  override fun onResume() {
    super.onResume()
    getPresenter().resume()
  }

  override fun onPause() {
    super.onPause()
    getPresenter().pause()
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    val state: Bundle? = getPresenter().saveState()
    if (state != null) {
      outState.putAll(state)
    }
  }

  override fun onDestroyView() {
    getPresenter().stop()
    super.onDestroyView()
  }
}