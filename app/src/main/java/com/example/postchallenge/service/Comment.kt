package com.example.postchallenge.service

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comment(
  var postId: Int = -1,
  var id: Int = -1,
  var name: String = "",
  var email: String = "",
  var body: String = ""
) : Parcelable