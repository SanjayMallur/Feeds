package com.smallur.feeds.data.source

import com.smallur.feeds.data.model.Post
import io.reactivex.Observable

class FakePostDataSource : PostDataSource{

    private val post1 = Post("Shaun Paul", "This is body of the post, This is body of the post", "https://picsum.photos/200/300/?random", "https://thumb.ibb.co/fb7YsJ/my_image.png", "Today 1:45pm", 123)
    private val post2 = Post("Jake W", "This is body of the post, This is body of the post,This is body of the post, This is body of the post,This is body of the post, This is body of the post,This is body of the post, This is body of the post", "https://picsum.photos/200/300?image=1080", "https://thumb.ibb.co/fb7YsJ/my_image.png", "Today 1:45pm", 120)

    override fun posts(): Observable<List<Post>> = Observable.just(listOf(this.post1, this.post2))
}