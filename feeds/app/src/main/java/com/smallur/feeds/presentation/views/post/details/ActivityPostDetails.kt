package com.smallur.feeds.presentation.views.post.details

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.sanjay.baselibrary.databinding.ContentBinding
import com.smallur.feeds.R
import com.smallur.feeds.data.model.Post
import com.smallur.feeds.presentation.base.views.ActivityDaggerBase

/**
 * Created by Sanjay
 * Activity for post details
 * */
class ActivityPostDetails : ActivityDaggerBase<ContentBinding>() {

    companion object {
        private const val EXTRAS_POST = "extrasPost"
        fun newIntent(context: Context?, post: Post): Intent {
            val intent = Intent(context, ActivityPostDetails::class.java)
            intent.putExtra(ActivityPostDetails.EXTRAS_POST, post)
            return intent
        }
    }

    private var fragmentPostDetails: FragmentPostDetails? = null

    override fun provideLayoutResId(): Int = R.layout.content

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentPostDetails = supportFragmentManager.findFragmentById(R.id.content) as? FragmentPostDetails
        if (fragmentPostDetails == null) {
            val post = intent.getParcelableExtra<Post>(EXTRAS_POST)
            fragmentPostDetails = FragmentPostDetails.newInstance(post)
            addFragmentToActivity(supportFragmentManager, fragmentPostDetails!!, R.id.content, FragmentPostDetails.TAG)
        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            supportFinishAfterTransition() // finishing activity after transition
        }
    }
}