package com.smallur.feeds.presentation.views.post.details

import com.sanjay.baselibrary.presentation.views.recycler.RecyclerViewContract
import com.smallur.feeds.data.model.Post

/**
 * Interface for post details
 * */
interface ContractPostDetails {

    interface View {
        fun showFullPostDetails(post: Post)
    }

    interface Presenter : RecyclerViewContract.Presenter {
        fun restoreData()
    }

}