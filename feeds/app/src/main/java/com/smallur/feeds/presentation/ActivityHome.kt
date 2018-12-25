package com.smallur.feeds.presentation

import android.os.Bundle
import com.smallur.feeds.R
import com.smallur.feeds.databinding.ActivityHomeBinding
import com.smallur.feeds.presentation.base.views.ActivityDaggerBase
import com.smallur.feeds.presentation.views.post.list.FragmentPosts

/**
 * Created by Sanjay
 *Activity for home screen
 * */
class ActivityHome : ActivityDaggerBase<ActivityHomeBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fragment = supportFragmentManager.findFragmentById(R.id.container)
        if (fragment == null) {
            addFragmentToActivity(supportFragmentManager, FragmentPosts.newInstance(), R.id.container, FragmentPosts.TAG) // adding fragment to activity
        }
    }

    override fun provideLayoutResId() = R.layout.activity_home

}