package com.demo.flickr.di

import android.app.Application
import com.demo.flickr.FlickrApplication
import com.demo.flickr.di.module.ActivityBuildersModule
import com.demo.flickr.di.module.DiBindingModule
import com.demo.flickr.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuildersModule::class,
        NetworkModule::class,
        DiBindingModule::class
    ]
)
interface AppComponent: AndroidInjector<FlickrApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}