package com.smallur.feeds.data.source.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.smallur.feeds.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Sanjay
 * Api client module to make request to back end
 * */
@Module(includes = [(HttpClientModule::class)])
class ApiClientModule {

    /**
     * Singleton instance of api
     * @param retrofit retrofit instance
     * @return postApi
     * */
    @Provides
    @Singleton
    fun apiInterface(retrofit: Retrofit): PostApi {
        return retrofit.create(PostApi::class.java)
    }

    /**
     * Singleton retrofit instance across application
     * @param gsonConverterFactory converter factory class for json response
     * @param okHttpClient okhttp client instance for server class
     * @param factory factory class for adapter
     * @return retrofit instance
     * */
    @Provides
    @Singleton
    fun retrofit(gsonConverterFactory: GsonConverterFactory, okHttpClient: OkHttpClient, @Named("rxJava2") factory: CallAdapter.Factory): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(factory)
                .client(okHttpClient)
                .baseUrl(BuildConfig.BASE_URL)
                .build()
    }

    /**
     * Singleton GsonConverterFactory instance
     * @param gson
     * */
    @Provides
    @Singleton
    fun gsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    /**
     * Singleton Gson instance
     * */
    @Provides
    @Singleton
    fun gson(): Gson {
        return GsonBuilder().create()
    }

    /**
     * Singleton CallAdapter.Factory instance
     * */
    @Provides
    @Singleton
    @Named("rxJava2")
    fun rxJava2CallAdapterFactory(): CallAdapter.Factory {
        return RxJava2CallAdapterFactory.create()
    }

}