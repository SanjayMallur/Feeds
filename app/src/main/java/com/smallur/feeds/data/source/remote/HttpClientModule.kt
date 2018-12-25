package com.smallur.feeds.data.source.remote

import android.util.Log
import com.smallur.feeds.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Sanjay
 * Http client module for ApiClient
 * */
@Module
class HttpClientModule {

    companion object {
        const val TAG = "Networking"
    }

    /**
     * Singleton OkHttpClient for Api client
     * @param loggingInterceptor logging interceptor for logs
     * */
    @Provides
    @Singleton
    fun okHttpClient(@Named("logging") loggingInterceptor: Interceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            // Logging Interceptor
            builder.addInterceptor(loggingInterceptor)
        }
        return builder.build()
    }

    /**
     * Singleton interceptor for logging
     * */
    @Provides
    @Singleton
    @Named("logging")
    fun loggingInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
            Log.i(TAG, message)
        })
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }
}
