package com.smallur.feeds.data.source

import com.smallur.feeds.data.DataModule
import com.smallur.feeds.data.source.remote.ApiClientModule
import com.smallur.feeds.data.source.remote.PostApi
import com.smallur.feeds.data.source.remote.PostRemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

/**
* Created by Sanjay
 * Post module to provide singleton postData source instance
* */
@Module(includes = [((ApiClientModule::class))])
class PostModule {

    @Provides
    @Singleton
    @Named(DataModule.REMOTE)
    fun postRemoteDataSource(apiClient: PostApi) : PostDataSource{
        return PostRemoteDataSource(apiClient) // singleton post remote data source instance
    }

}