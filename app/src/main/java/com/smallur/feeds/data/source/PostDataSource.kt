package com.smallur.feeds.data.source

import com.smallur.feeds.data.model.Post
import io.reactivex.Observable

/**
 * Created by Sanjay
 * Data source interface for posts
* */
interface PostDataSource {
    fun posts(): Observable<List<Post>>
}