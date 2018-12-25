package com.smallur.feeds.data

import com.smallur.feeds.data.model.Post
import com.smallur.feeds.data.source.PostDataSource
import io.reactivex.Observable

/**
* Created by Sanjay
 * Repository for providing data from remote/local data source
* */
class PostRepository(private val remoteDataSource: PostDataSource): PostDataSource {

    override fun posts(): Observable<List<Post>> =
         this.remoteDataSource.posts()// returning data from remote data source

}