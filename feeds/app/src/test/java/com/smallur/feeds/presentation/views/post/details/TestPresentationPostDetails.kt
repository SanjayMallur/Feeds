package com.smallur.feeds.presentation.views.post.details

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.smallur.feeds.data.model.Post
import org.junit.Before
import org.junit.Test

class TestPresentationPostDetails {

    private lateinit var presenterPostDetails: PresenterPostDetails
    private val view: ContractPostDetails.View = mock()
    private val post = Post("testName", "Body", "https://picsum.photos/200/300/?random", "https://thumb.ibb.co/fb7YsJ/my_image.png", "Today 1:45pm", 123)

    @Before
    fun setUp(){
        this.presenterPostDetails = PresenterPostDetails(this.view, this.post)
    }

    @Test
    fun testStart(){
        this.presenterPostDetails.start()
        verify(this.view).showFullPostDetails(this.post)
    }

    @Test
    fun testStartRestoreData(){
        this.presenterPostDetails.restoreData()
        verify(this.view).showFullPostDetails(this.post)
    }

    @Test
    fun testOnTryAgain(){
        this.presenterPostDetails.onTryAgain()
    }

    @Test
    fun testRefresh(){
        this.presenterPostDetails.refresh()
    }
}