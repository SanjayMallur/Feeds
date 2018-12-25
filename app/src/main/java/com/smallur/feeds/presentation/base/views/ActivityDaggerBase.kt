package com.smallur.feeds.presentation.base.views

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.sanjay.baselibrary.presentation.views.ActivityBase
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Base activity for dagger implemented activities
 * */
abstract class ActivityDaggerBase<T : ViewDataBinding> : ActivityBase<T>(), HasSupportFragmentInjector {

    @Inject
    lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)// activity injection
        super.onCreate(savedInstanceState)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return supportFragmentInjector // fragment injector
    }

}