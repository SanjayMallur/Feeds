package com.sanjay.baselibrary.presentation.views

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Build
import android.os.Bundle
import android.support.annotation.ColorInt
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.WindowManager

/**
 * Created by Sanjay
 * Base activity class to handle common actions for all activities
 */
abstract class ActivityBase<T : ViewDataBinding> : AppCompatActivity() {

    protected var mBinding: T? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.mBinding = DataBindingUtil.setContentView(this, provideLayoutResId())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        this.mBinding = null
        super.onDestroy()
    }

    /**
     * method to set status bar color
     * @param color color code to status bar
     * */
    fun setStatusBarColor(@ColorInt color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = color
        }
    }

    @LayoutRes
    protected abstract fun provideLayoutResId(): Int

    /**
     * Method to add fragment to activity
     * @param fragmentManager manager class of fragment
     * @param fragment fragment to add
     * @param frameId frame id
     * @param tag fragment tag
     * */
    fun addFragmentToActivity(fragmentManager: FragmentManager, fragment: Fragment, frameId: Int, tag: String) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(frameId, fragment, tag)
        fragmentTransaction.commit()
    }
}
