package com.smallur.feeds.presentation.views.post.list

import com.sanjay.baselibrary.presentation.PresenterBaseImpl
import com.smallur.feeds.data.exception.DataExceptionHelper
import com.smallur.feeds.data.source.PostDataSource
import com.smallur.feeds.presentation.base.SchedulerFacade
import com.smallur.feeds.presentation.base.scopes.PerFragment
import javax.inject.Inject

/**
 * Created by Sanjay
 * The Presenter to handle posts actions and updating UI with data
 * @param view injecting [FragmentPosts]
 * @param postDataSource injecting [PostDataSource]
 * @param schedulersFacade injecting [SchedulerFacade]
 */
@PerFragment
class PresenterPost @Inject constructor(
        view: ContractPosts.View,
        private val postDataSource: PostDataSource,
        private val schedulersFacade: SchedulerFacade) : PresenterBaseImpl<ContractPosts.View>(view), ContractPosts.Presenter {


    override fun start() {
        loadPosts() // loading posts from data source
    }

    override fun refresh() {
        loadPosts()
    }

    override fun onTryAgain() {
        this.view.showProgress(true)
        loadPosts()
    }

    private fun loadPosts() {
        this.view.showProgress(true)
        addDisposable(this.postDataSource.posts()
                .subscribeOn(this.schedulersFacade.io())
                .observeOn(this.schedulersFacade.ui())
                .subscribe(
                        { posts -> this.view.showPosts(posts) }, // updating ui with posts
                        { throwable -> this.view.showError(DataExceptionHelper(throwable).dataErrorType(), null) },// displaying error
                        { this.view.showProgress(false) }
                ))
    }

}