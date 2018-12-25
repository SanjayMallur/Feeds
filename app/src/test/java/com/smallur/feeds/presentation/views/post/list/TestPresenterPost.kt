package com.smallur.feeds.presentation.views.post.list

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.sanjay.baselibrary.data.helper.DataErrorType
import com.smallur.feeds.data.model.Post
import com.smallur.feeds.data.source.PostDataSource
import com.smallur.feeds.presentation.base.TestSchedulerFacadeImpl
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class TestPresenterPost {

    private lateinit var presenterPost: PresenterPost
    private val view: ContractPosts.View = mock()
    private val postDataSource: PostDataSource = mock()
    private val testScheduler = TestScheduler()
    private val post = Post("testName", "Body", "https://picsum.photos/200/300/?random", "https://thumb.ibb.co/fb7YsJ/my_image.png", "Today 1:45pm", 123)

    @Before
    fun setUp(){
        this.presenterPost = PresenterPost(this.view, this.postDataSource, TestSchedulerFacadeImpl(this.testScheduler))
    }


    @Test
    fun testStart(){
        Mockito.`when`(this.postDataSource.posts()).thenReturn(Observable.just(listOf(this.post)))
        this.presenterPost.start()
        verify(this.view).showProgress(true)
        this.testScheduler.triggerActions()
        verify(this.view).showPosts(listOf(this.post))
        verify(this.view).showProgress(false)
    }

    @Test
    fun testStart_Error(){
        Mockito.`when`(this.postDataSource.posts()).thenReturn(Observable.error { RuntimeException() })
        this.presenterPost.start()
        verify(this.view).showProgress(true)
        this.testScheduler.triggerActions()
        verify(this.view).showError(DataErrorType.UNKNOWN, null)
    }

    @Test
    fun testRefresh(){
        Mockito.`when`(this.postDataSource.posts()).thenReturn(Observable.just(listOf(this.post)))
        this.presenterPost.refresh()
        verify(this.view).showProgress(true)
        this.testScheduler.triggerActions()
        verify(this.view).showPosts(listOf(this.post))
    }

    @Test
    fun testTryAgain(){
        Mockito.`when`(this.postDataSource.posts()).thenReturn(Observable.just(listOf(this.post)))
        this.presenterPost.onTryAgain()
        this.testScheduler.triggerActions()
        verify(this.view).showPosts(listOf(this.post))
        verify(this.view).showProgress(false)
    }
}