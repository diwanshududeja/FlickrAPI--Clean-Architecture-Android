package com.demo.flickr.data.vo

import androidx.annotation.Keep

@Keep
data class FlickrResponse(
    val photos: Photos
)

@Keep
data class PhotoDetail(
    var id: String? = null,
    val url_m: String? = null,
    val title: String? = null
)

@Keep
data class Photos(
    val photo: List<PhotoDetail> = listOf()
)




