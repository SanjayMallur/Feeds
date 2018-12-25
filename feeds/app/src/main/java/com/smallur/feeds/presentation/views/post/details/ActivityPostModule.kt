package com.smallur.feeds.presentation.views.post.details

import com.smallur.feeds.presentation.base.scopes.PerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Sanjay
 * Class provides [FragmentPostDetails] instance
 * */
@Module
abstract class ActivityPostModule {

    @PerFragment
    @ContributesAndroidInjector(modules = [(FragmentPostDetailsModule::class)])
    abstract fun bindFragmentPost(): FragmentPostDetails // FragmentPostDetails instance
}