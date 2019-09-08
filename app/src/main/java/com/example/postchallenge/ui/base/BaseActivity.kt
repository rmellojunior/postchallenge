package com.example.postchallenge.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.postchallenge.PostApplication

abstract class BaseActivity : AppCompatActivity(), BaseContract.View {

  open val layout: Int = -1

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    (application as PostApplication).netComponent.inject(this)
    setContentView(layout)
  }

}