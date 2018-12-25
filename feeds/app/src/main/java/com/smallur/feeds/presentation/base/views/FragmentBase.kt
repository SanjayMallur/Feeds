package com.smallur.feeds.presentation.base.views

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Sanjay
 * Base class for all fragments
 * */
abstract class FragmentBase<V : ViewDataBinding> : Fragment() {

    //view for binding
    lateinit var binding: V

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.binding = DataBindingUtil.inflate(inflater, provideLayoutResId(), container, false)
        initView(inflater, savedInstanceState)
        return this.binding.root
    }

    @LayoutRes
    protected abstract fun provideLayoutResId(): Int // providing layout id

    /**
     * Method to init views
     * @param inflater type of the inflater
     * @param savedInstanceState bundle data
     * */
    protected abstract fun initView(inflater: LayoutInflater, savedInstanceState: Bundle?)
}