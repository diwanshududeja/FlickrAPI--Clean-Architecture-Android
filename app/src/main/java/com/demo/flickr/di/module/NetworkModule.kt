package com.demo.flickr.di.module

import com.demo.flickr.data.service.RetrofitService
import com.demo.flickr.utils.Constants
import dagger.Module
import dagger.Provides
import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import java.util.logging.Level


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