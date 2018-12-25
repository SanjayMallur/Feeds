package com.smallur.feeds


import android.app.Activity
import com.sanjay.baselibrary.ApplicationBaseConfig
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by Sanjay
 * Base application class extending [ApplicationBaseConfig] class
 * */
class ApplicationConfig : ApplicationBaseConfig(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().application(this).build().inject(this) // builder app component
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
        return activityInjector
    }
}