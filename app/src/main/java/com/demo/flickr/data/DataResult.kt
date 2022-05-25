package com.demo.flickr.data

import com.demo.flickr.data.vo.PhotoDetail

sealed class DataResult {
    data class Success(val data: List<PhotoDetail>) : DataResult()
    data class Error(val error: String) : DataResult()
}