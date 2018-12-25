package com.smallur.feeds.presentation.views

import com.smallur.feeds.presentation.base.scopes.PerActivity
import com.smallur.feeds.presentation.base.scopes.PerFragment
import com.smallur.feeds.presentation.views.post.list.FragmentPosts
import com.smallur.feeds.presentation.views.post.list.FragmentPostsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Sanjay
 * Class to provide FragmentPosts instance
 * */
@Module
@PerActivity
abstract class ActivityHomeModule {

    @PerFragment
    @ContributesAndroidInjector(modules = [(FragmentPostsModule::class)])
    abstract fun bindFragmentPosts(): FragmentPosts // FragmentPosts instance
}