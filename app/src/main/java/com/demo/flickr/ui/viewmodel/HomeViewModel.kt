package com.demo.flickr.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.flickr.data.DataResult
import com.demo.flickr.domain.HomeRepository
import com.demo.flickr.ui.state.HomeScreenState
import kotlinx.coroutines.*

class HomeViewModel constructor(private val homeRepository: HomeRepository?) : ViewModel() {
    val state =  MutableLiveData<HomeScreenState>()
    private var searchText :  String = ""

    fun getPhotosList(searchTag: String) {
        viewModelScope.launch {
            getDataFromAPI(searchTag)
        }
    }

    fun changeSearchTextInput(newText: String = ""){
        searchText = newText
    }

    fun onSearchClick(){
        getPhotosList(searchText)
    }

    private suspend fun getDataFromAPI(searchTag: String) {
        state.postValue(HomeScreenState.Loading)
        when (val dataResult = homeRepository?.loadAPIData(searchTag)) {
            is DataResult.Success -> {
                state.postValue(HomeScreenState.Images(dataResult.data))
            }
            is DataResult.Error -> {
                state.postValue(HomeScreenState.Error(dataResult.error))
            }
        }
    }


}

