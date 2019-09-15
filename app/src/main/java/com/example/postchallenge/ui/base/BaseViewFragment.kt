package com.example.postchallenge.ui.base

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import io.reactivex.Observable

abstract class BaseViewFragment<T : BaseContract.Presenter> : BaseFragment(), BaseContract.View {

  abstract fun getPresenter(): T

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    getPresenter().setInitialState(savedInstanceState ?: arguments)
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    val state: Bundle? = getPresenter().getCurrentState()
    if (state != null) {
      outState.putAll(state)
    }
  }

  override fun onViewStateRestored(savedInstanceState: Bundle?) {
    super.onViewStateRestored(savedInstanceState)
    getPresenter().setInitialState(savedInstanceState)
  }

  override fun onStart() {
    super.onStart()
    getPresenter().start()
  }

  override fun onResume() {
    super.onResume()
    getPresenter().resume()
  }

  override fun onPause() {
    getPresenter().pause()
    super.onPause()
  }

  override fun onStop() {
    getPresenter().stop()
    super.onStop()
  }

  override fun isInternetOn(): Observable<Boolean> {
    val connectivityManager = context!!.getSystemService(
      Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return Observable.just(activeNetworkInfo != null && activeNetworkInfo.isConnected)
  }

}