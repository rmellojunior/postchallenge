package com.example.postchallenge.service

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PostItem(
  var userId: Int = -1,
  var id: Int = -1,
  var title: String = "",
  var body: String = ""
) : Parcelable