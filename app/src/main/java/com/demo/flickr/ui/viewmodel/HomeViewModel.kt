package com.demo.flickr.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.flickr.data.DataResult
import com.demo.flickr.data.vo.PhotoDetail
import com.demo.flickr.domain.HomeRepository
import kotlinx.coroutines.*

class HomeViewModel constructor(private val homeRepository: HomeRepository?) : ViewModel() {


    val photosList = MutableLiveData<List<PhotoDetail>>()
    val errorMessage = MutableLiveData<String>()

    fun getPhotosList(searchTag: String) {
        viewModelScope.launch {
            getDataFromAPI(searchTag)
        }
    }

    private suspend fun getDataFromAPI(searchTag: String) {
        when (val dataResult = homeRepository?.loadAPIData(searchTag)) {
            is DataResult.Success -> {
                photosList.postValue(dataResult.data)
            }
            is DataResult.Error -> {
                errorMessage.postValue(dataResult.error)
            }
        }
    }


}

