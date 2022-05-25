package com.demo.flickr.domain

import com.demo.flickr.data.DataResult
import com.demo.flickr.data.source.RemoteDataSource
import com.demo.flickr.utils.Constants
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource): HomeRepository {

    override suspend fun loadAPIData(searchTag: String): DataResult {
        val response = remoteDataSource.getAPIData(searchTag)
        return if (response.isSuccessful) {
            val responseData = response.body()
            val photosList = responseData?.photos?.photo
            if(photosList?.isNotEmpty() == true){
                DataResult.Success(photosList)
            }else{
                DataResult.Error(Constants.MESSAGE_EMPTY_LIST)
            }
        } else {
            DataResult.Error(Constants.API_ISSUE)
        }
    }

}