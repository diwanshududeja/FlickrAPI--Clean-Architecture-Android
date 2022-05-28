package com.demo.flickr.ui.state

import com.demo.flickr.data.vo.PhotoDetail

sealed class HomeScreenState {
    object Loading : HomeScreenState()
    data class Error(val errorMessage: String) : HomeScreenState()
    data class Images(val photos: List<PhotoDetail> = emptyList()) : HomeScreenState()
}