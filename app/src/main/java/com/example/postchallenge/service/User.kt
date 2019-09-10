package com.example.postchallenge.service

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
  var id: Int = -1,
  var name: String = "",
  var username: String = ""
) : Parcelable