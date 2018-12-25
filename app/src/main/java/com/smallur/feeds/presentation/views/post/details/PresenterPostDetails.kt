package com.smallur.feeds.presentation.views.post.details

import com.sanjay.baselibrary.presentation.PresenterBaseImpl
import com.smallur.feeds.data.model.Post
import javax.inject.Inject

/**
 * Created by Sanjay
 * Presenter to handle UI actions and updating UI
 * @param view injecting [FragmentPostDetails]
 * @param post injecting [Post]
 * */
class PresenterPostDetails @Inject constructor(view: ContractPostDetails.View,
                                               private val post: Post) : PresenterBaseImpl<ContractPostDetails.View>(view), ContractPostDetails.Presenter {


    override fun restoreData() {
        this.view.showFullPostDetails(this.post) // updating ui with re stored data
    }


    override fun start() {
        this.view.showFullPostDetails(this.post) // displaying post details
    }

    override fun refresh() {
        //Not required
    }

    override fun onTryAgain() {
        //Not required
    }
}