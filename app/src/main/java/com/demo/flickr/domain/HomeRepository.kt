package com.demo.flickr.domain

import com.demo.flickr.data.DataResult

interface HomeRepository {
     suspend fun loadAPIData(searchTag: String): DataResult
}