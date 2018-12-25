package com.smallur.feeds.presentation.views.post.details

import com.smallur.feeds.data.model.Post
import com.smallur.feeds.presentation.base.scopes.PerFragment
import dagger.Module
import dagger.Provides

/**
 * Created by Sanjay
 * Class provides [FragmentPostDetails], [PresenterPostDetails]  and [Post] instances
 * */
@Module
class FragmentPostDetailsModule {

    @Provides
    @PerFragment
    fun provideView(fragmentPostDetails: FragmentPostDetails): ContractPostDetails.View = fragmentPostDetails // FragmentPostDetails instance

    @Provides
    @PerFragment
    fun providePresenter(presenterPosts: PresenterPostDetails): ContractPostDetails.Presenter = presenterPosts // PresenterPostDetails instance

    @Provides
    fun providePost(fragment: FragmentPostDetails): Post = fragment.post // Post instance
}