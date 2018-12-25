package com.smallur.feeds.data

import com.smallur.feeds.data.source.FakePostDataSource
import com.smallur.feeds.data.source.PostDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
@Module
class DataModule {
    companion object {
        const val REMOTE = "remote"
    }
    @Provides
    @Singleton
    fun postRepository(): PostDataSource = FakePostDataSource()
}