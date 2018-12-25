package com.smallur.feeds.presentation.views.post.list

import com.sanjay.baselibrary.presentation.views.recycler.RecyclerViewContract
import com.smallur.feeds.data.model.Post

/**
* Created by Sanjay
* Interface for posts
* */
interface ContractPosts {

    interface View : RecyclerViewContract.View {
        fun showPosts(posts: List<Post>)
    }

    interface Presenter : RecyclerViewContract.Presenter
}