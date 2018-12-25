package com.smallur.feeds.presentation.views.welcome

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.sanjay.baselibrary.presentation.views.ActivityBase
import com.smallur.feeds.R
import com.smallur.feeds.databinding.ActivityWelcomeBinding
import com.smallur.feeds.presentation.ActivityHome

/**
 * Created by Sanjay
 * Activity for welcome screen
 * */
class ActivityWelcome : ActivityBase<ActivityWelcomeBinding>() {

    override fun provideLayoutResId() = R.layout.activity_welcome

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN // activity full screen

        mBinding?.appCompatButtonLogin?.setOnClickListener {
            startActivity(Intent(applicationContext, ActivityHome::class.java))
            finish()
        }
    }
}