package com.smallur.feeds.presentation.views.post.list

import com.smallur.feeds.presentation.base.scopes.PerFragment
import dagger.Module
import dagger.Provides

/**
 * Created by Sanjay
 * class to provide [FragmentPosts] and [PresenterPost] instances
 * */
@PerFragment
@Module
class FragmentPostsModule {

    @Provides
    @PerFragment
    fun provideView(fragmentPosts: FragmentPosts): ContractPosts.View = fragmentPosts // FragmentPost instance

    @Provides
    @PerFragment
    fun providePresenter(presenterPosts: PresenterPost): ContractPosts.Presenter = presenterPosts // PresenterPost instance
}