package com.shubhadeep.app.types

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FeedItem(
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String,
    var isLiked: Boolean,
) : Parcelable

