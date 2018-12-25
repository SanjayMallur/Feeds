package com.smallur.feeds.data

import com.nhaarman.mockito_kotlin.mock
import com.smallur.feeds.data.model.Post
import com.smallur.feeds.data.source.PostDataSource
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import kotlin.test.assertEquals

class TestPostRepository {

    private lateinit var postRepository: PostRepository
    private val postDataSource: PostDataSource = mock()
    private val post = Post("testName", "Body", "https://picsum.photos/200/300/?random", "https://thumb.ibb.co/fb7YsJ/my_image.png", "Today 1:45pm", 123)

    @Before
    fun setUp(){
        this.postRepository = PostRepository(this.postDataSource)
    }

    @Test
    fun testPosts(){
        Mockito.`when`(this.postDataSource.posts()).thenReturn(Observable.just(listOf(this.post)))
        val response = this.postRepository.posts().blockingFirst()
        assertEquals("testName", response[0].name)
        assertEquals("Body", response[0].body)
        assertEquals("https://picsum.photos/200/300/?random", response[0].url)
        assertEquals("https://thumb.ibb.co/fb7YsJ/my_image.png", response[0].thumbNailUrl)
        assertEquals("Today 1:45pm", response[0].date)
        assertEquals(123, response[0].likes)
    }
}