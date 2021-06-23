package com.example.hw7

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
class Article(@Json(name = "title") val title: String,
                   @Json(name = "section") val section: String,
                   @Json(name = "published_date") val date: String,
                   @Json(name = "url") val url: String) : Parcelable {
}