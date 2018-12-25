package com.smallur.feeds.presentation.views.post.details

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import com.sanjay.baselibrary.app.helper.withArgs
import com.smallur.feeds.R
import com.smallur.feeds.data.model.Post
import com.smallur.feeds.databinding.FragmentPostDetailsBinding
import com.smallur.feeds.presentation.base.views.FragmentDaggerBase

/**
 *Created by Sanjay
 * Fragment for post details
 * */
class FragmentPostDetails : FragmentDaggerBase<FragmentPostDetailsBinding, ContractPostDetails.Presenter>(), ContractPostDetails.View {

    lateinit var post: Post
    private var flag = true

    companion object {
        const val TAG = "FragmentPostDetails"
        private const val ARG_POST = "argPost"
        fun newInstance(post: Post) = FragmentPostDetails().withArgs {
            putParcelable(ARG_POST, post)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun provideLayoutResId(): Int = R.layout.fragment_post_details

    override fun onAttach(context: Context?) {
        this.post = arguments?.getParcelable(ARG_POST)!!
        super.onAttach(context)

    }

    override fun initView(inflater: LayoutInflater, savedInstanceState: Bundle?) {
        this.presenter.start()
        this.binding.image.setOnClickListener { activity!!.onBackPressed() } // finishing activity
        this.binding.fab.setOnClickListener {
            if (flag) {
                this.binding.fab.setImageDrawable(ContextCompat.getDrawable(context as Activity, R.drawable.like))
                this.binding.textLikes.setText((this.binding.post!!.likes + 1).toString()) // updating text view
                flag = false
            } else if (!flag) {
                this.binding.fab.setImageDrawable(ContextCompat.getDrawable(context as Activity, R.drawable.unlike))
                this.binding.textLikes.setText((this.binding.post!!.likes - 1).toString()) // updating text view
                flag = true
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState != null) {
            this.presenter.restoreData() // restoring data on rotation
        }
    }

    override fun showFullPostDetails(post: Post) {
        this.binding.post = post
    }


}