package com.smallur.feeds.data

import com.smallur.feeds.data.source.PostDataSource
import com.smallur.feeds.data.source.PostModule
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [PostModule::class])
class DataModule {

    companion object {
        const val REMOTE = "remote"
        const val LOCAL = "local"
    }

    @Provides
    @Singleton
    fun postRepository(@Named(REMOTE) remoteDataSource: PostDataSource): PostDataSource = PostRepository(remoteDataSource)
}