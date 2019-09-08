package com.example.postchallenge.utils.extensions

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.example.postchallenge.ui.base.BaseActivity

fun BaseActivity.addFragment(
  fragment: Fragment,
  @IdRes containerViewId: Int
) {
  val ft = supportFragmentManager.beginTransaction()
    .replace(containerViewId, fragment)
  if (!supportFragmentManager.isStateSaved) {
    ft.commit()
  }
}