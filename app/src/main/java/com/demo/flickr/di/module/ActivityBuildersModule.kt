package com.demo.flickr.di.module

import com.demo.flickr.ui.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMyActivity(): HomeActivity
}