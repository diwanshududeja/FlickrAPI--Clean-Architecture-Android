package com.demo.flickr.data.source

import com.demo.flickr.data.service.RetrofitService
import com.demo.flickr.data.vo.FlickrResponse
import com.demo.flickr.utils.Constants
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val retrofitService: RetrofitService) : RemoteDataSource{

    override suspend fun getAPIData(searchQuery: String): Response<FlickrResponse> {
        return retrofitService.getPhotos(Constants.API_KEY, Constants.API_SERVICE_NAME, searchQuery, Constants.PAGE_COUNT, Constants.PAGE_NUMBER)
    }

}