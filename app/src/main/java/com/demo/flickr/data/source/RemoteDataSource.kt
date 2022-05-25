package com.demo.flickr.data.source

import com.demo.flickr.data.vo.FlickrResponse
import retrofit2.Response

interface RemoteDataSource {
     suspend fun getAPIData(searchQuery: String): Response<FlickrResponse>
}