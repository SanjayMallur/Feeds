package com.smallur.feeds.data.source.remote

import com.nhaarman.mockito_kotlin.mock
import com.smallur.feeds.data.model.Post
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import kotlin.test.assertEquals

class TestPostRemoteDataSource {

    private lateinit var postRemoteDataSource: PostRemoteDataSource
    private val postApi: PostApi = mock()
    private val post = Post("testName", "Body", "https://picsum.photos/200/300/?random", "https://thumb.ibb.co/fb7YsJ/my_image.png", "Today 1:45pm", 123)

    @Before
    fun setUp(){
        this.postRemoteDataSource = PostRemoteDataSource(this.postApi)
    }

    @Test
    fun testPosts(){
        Mockito.`when`(this.postApi.getPosts()).thenReturn(Observable.just(listOf(this.post)))
       val response =  this.postRemoteDataSource.posts().blockingFirst()
        assertEquals("testName", response[0].name)
        assertEquals("Body", response[0].body)
        assertEquals("https://picsum.photos/200/300/?random", response[0].url)
        assertEquals("https://thumb.ibb.co/fb7YsJ/my_image.png", response[0].thumbNailUrl)
        assertEquals("Today 1:45pm", response[0].date)
        assertEquals(123, response[0].likes)
    }
}