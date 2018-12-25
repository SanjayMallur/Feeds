package com.smallur.feeds.presentation.base.views

import android.content.Context
import android.databinding.ViewDataBinding
import android.support.v4.app.Fragment
import com.sanjay.baselibrary.presentation.PresenterBase
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Created by Sanjay
 * Base fragment for all dagger injected fragments
 * */
abstract class FragmentDaggerBase<V : ViewDataBinding, P : PresenterBase> : FragmentBase<V>(), HasSupportFragmentInjector {

    @Inject
    protected lateinit var presenter: P

    @Inject
    lateinit var childFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onDestroyView() {
        this.presenter.stop()
        super.onDestroyView()
    }

    override fun onDestroy() {
        this.presenter.destroy()
        super.onDestroy()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return childFragmentInjector
    }
}