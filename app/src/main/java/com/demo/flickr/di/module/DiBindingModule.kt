package com.demo.flickr.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demo.flickr.data.source.RemoteDataSource
import com.demo.flickr.data.source.RemoteDataSourceImpl
import com.demo.flickr.di.ViewModelKey
import com.demo.flickr.factory.HomeViewModelFactory
import com.demo.flickr.domain.HomeRepository
import com.demo.flickr.domain.HomeRepositoryImpl
import com.demo.flickr.ui.viewmodel.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class DiBindingModule {

    @Binds
    abstract fun bindsRemoteDataSource(remoteDataSource: RemoteDataSourceImpl): RemoteDataSource

    @Binds
    abstract fun bindsRepository(repository: HomeRepositoryImpl): HomeRepository

    @Binds
    abstract fun bindViewModelFactory(factory: HomeViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun providesViewModel(homeViewModel: HomeViewModel): ViewModel


}