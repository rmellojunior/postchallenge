package com.example.postchallenge.ui.base

import android.os.Bundle

interface BaseContract {

  interface Presenter {
    fun start(state: Bundle?)
    fun resume()
    fun pause()
    fun stop()

    fun saveState(): Bundle?
  }

  interface View

}