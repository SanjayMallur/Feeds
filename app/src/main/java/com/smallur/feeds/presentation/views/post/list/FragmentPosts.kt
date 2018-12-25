package com.smallur.feeds.presentation.views.post.list

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.*
import com.sanjay.baselibrary.presentation.views.recycler.AdapterBase
import com.smallur.feeds.R
import com.smallur.feeds.data.model.Post
import com.smallur.feeds.databinding.ActivityPostBinding
import com.smallur.feeds.presentation.base.views.FragmentDaggerRecyclerViewBase
import com.smallur.feeds.presentation.views.post.AdapterPosts
import com.smallur.feeds.presentation.views.post.details.ActivityPostDetails

/*
* Created by Sanjay
* */

class FragmentPosts : FragmentDaggerRecyclerViewBase<ActivityPostBinding, ContractPosts.Presenter>(), ContractPosts.View, AdapterBase.IAdapterBaseListener<Post> {

    companion object {
        const val TAG = "FragmentPosts"
        fun newInstance(): FragmentPosts = FragmentPosts()
    }

    private val adapter = AdapterPosts()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true // retaining same instance on rotation
    }

    override fun provideLayoutResId(): Int = R.layout.activity_post

    override fun provideLayoutManager(): RecyclerView.LayoutManager {
        return StaggeredGridLayoutManager(resources.getInteger(R.integer.default_column_number), StaggeredGridLayoutManager.VERTICAL)
    }

    override fun provideItemAnimator(): Int = R.anim.layout_animation_from_bottom // proving bottom animator for recyler view

    override fun provideAdapter(): AdapterBase<Post> {
        return adapter // providing adapter
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun initView(inflater: LayoutInflater, savedInstanceState: Bundle?) {
        super.initView(inflater, savedInstanceState)
    }


    override fun onResume() {
        super.onResume()
        this.adapter.setListener(this)

    }

    override fun onPause() {
        adapter.setListener(null) // removing  adapter listener
        super.onPause()
    }


    override fun showPosts(posts: List<Post>) {
        if (isRefreshing()) {
            this.adapter.list = posts
            if (!posts.isEmpty()) {
                this.recyclerView.scrollToPosition(0)
            }
        } else this.adapter.addList(posts)
    }

    override fun onItemSelected(view: View, list: MutableList<Post>, position: Int) {
        this.adapter.setListener(null)

        val imageView = this.binding.root.findViewById<View>(R.id.fullImage)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity!!, imageView,
                getString(R.string.transition_post_image)).toBundle()
        ActivityCompat.startActivity(context!!, ActivityPostDetails.newIntent(context, list[position]), options)
    }
}