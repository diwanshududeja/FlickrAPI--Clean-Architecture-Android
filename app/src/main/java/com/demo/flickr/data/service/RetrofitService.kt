package com.demo.flickr.data.service

import com.demo.flickr.data.vo.FlickrResponse
import com.demo.flickr.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface RetrofitService {

    @Headers(
        Constants.CONTENT_TYPE,
    )
    @GET("/services/rest?format=json&nojsoncallback=true&extras=media&extras=url_sq&extras=url_m")
    suspend fun getPhotos(
        @Query("api_key") apiKey: String,
        @Query("method") serviceName: String,
        @Query("tags") searchTag: String,
        @Query("per_page") pageCount: Int,
        @Query("page") pageNumber: Int,
    ): Response<FlickrResponse>


}