package com.demo.flickr.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demo.flickr.domain.HomeRepository
import com.demo.flickr.ui.viewmodel.HomeViewModel
import javax.inject.Inject

class HomeViewModelFactory @Inject constructor(private val repository: HomeRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            HomeViewModel(repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}