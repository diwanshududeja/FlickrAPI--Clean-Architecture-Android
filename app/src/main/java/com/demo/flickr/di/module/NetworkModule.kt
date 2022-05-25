package com.demo.flickr.di.module

import com.demo.flickr.data.service.RetrofitService
import com.demo.flickr.utils.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
public class NetworkModule {

    @Provides
    fun provideRetrofit(): RetrofitService{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitService::class.java)
    }
}