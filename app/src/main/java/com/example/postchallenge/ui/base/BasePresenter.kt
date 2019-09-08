package com.example.postchallenge.ui.base

import android.os.Bundle
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter : BaseContract.Presenter {

  private lateinit var onResumeSubscriptions: CompositeDisposable
  private lateinit var onStartSubscriptions: CompositeDisposable

  override fun start(state: Bundle?) {
    onStartSubscriptions = CompositeDisposable()
  }

  override fun resume() {
    onResumeSubscriptions = CompositeDisposable()
  }

  override fun pause() {
    if (!onResumeSubscriptions.isDisposed) {
      onResumeSubscriptions.dispose()
    }
  }

  override fun stop() {
    if (!onStartSubscriptions.isDisposed) {
      onStartSubscriptions.dispose()
    }
  }

  protected fun addOnResumeSubscription(subscription: Disposable) {
    onResumeSubscriptions.add(subscription)
  }

  protected fun addOnStartSubscription(subscription: Disposable) {
    onStartSubscriptions.add(subscription)
  }
}