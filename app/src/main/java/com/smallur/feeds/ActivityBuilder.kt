package com.smallur.feeds

import com.smallur.feeds.presentation.ActivityHome
import com.smallur.feeds.presentation.base.scopes.PerActivity
import com.smallur.feeds.presentation.views.ActivityHomeModule
import com.smallur.feeds.presentation.views.post.details.ActivityPostDetails
import com.smallur.feeds.presentation.views.post.details.ActivityPostModule
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

/**
 * Created by Sanjay
 * Module builder for all activity class instances
 * */
@Module(includes = [AndroidSupportInjectionModule::class])
abstract class ActivityBuilder {

    @PerActivity
    @ContributesAndroidInjector(modules = [(ActivityHomeModule::class)])
    abstract fun bindActivityHome(): ActivityHome // ActivityHome instance

    @PerActivity
    @ContributesAndroidInjector(modules = [(ActivityPostModule::class)])
    abstract fun bindActivityPost(): ActivityPostDetails // ActivityPostDetails instance
}