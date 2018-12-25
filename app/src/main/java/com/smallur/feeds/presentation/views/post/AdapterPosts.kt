package com.smallur.feeds.presentation.views.post

import com.sanjay.baselibrary.presentation.views.recycler.AdapterBase
import com.smallur.feeds.BR
import com.smallur.feeds.R
import com.smallur.feeds.data.model.Post

/**
 * Created by Sanjay
 * Adapter class for posts
 * */
class AdapterPosts : AdapterBase<Post>() {

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_post
    }

    override fun getItemResId(viewType: Int): Int {
        return viewType
    }

    override fun getVariableId(viewType: Int): Int {
        return BR.post
    }
}